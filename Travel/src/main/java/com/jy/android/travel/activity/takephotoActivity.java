package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jy.android.travel.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class takephotoActivity extends Activity implements View.OnClickListener{
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private Button take_photo,ok_btn;
    private ImageView picture;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_takephoto_activity);
        take_photo=(Button)findViewById(R.id.take_photo);
        picture=(ImageView)findViewById(R.id.xiangji_photo);
        take_photo.setOnClickListener(this);
        ok_btn=(Button)findViewById(R.id.take_photo_ok);
        ok_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.take_photo:
                File outputImage = new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO); // 启动相机程序
                break;
            case R.id.take_photo_ok:
                Intent imageurldata=new Intent();
                imageurldata.putExtra("data",""+imageUri);
                setResult(RESULT_OK,imageurldata);
               // Toast.makeText(takephotoActivity.this, ""+imageUri, Toast.LENGTH_SHORT).show();
                Log.d("takephoto", ""+imageUri);
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    Toast.makeText(takephotoActivity.this, ""+imageUri, Toast.LENGTH_SHORT).show();
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream
                                (getContentResolver()
                                        .openInputStream(imageUri));
                        picture.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
