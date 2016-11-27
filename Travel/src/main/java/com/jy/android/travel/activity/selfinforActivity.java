package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class selfinforActivity extends Activity implements View.OnClickListener{
    private ImageView headImage,headtitleimage;
   // SQLiteDataOpt sql=new SQLiteDataOpt();
    private final static String HEAD_NAME="head.txt";
    private final static String NAME_NAME="nichen.txt";
    private final static String SEX_NAME="sex.txt";
    private TextView nicheng,sexself;
    private TextView mTitleText;
    private TextView mAdressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_selfinfor);
        findview();
        init();
    }
    public void findview(){
        /*View sp1=findViewById(R.id.self_info_span1);
        sp1.setBackgroundColor(Color.rgb(205,197,191 ));
        View sp2=findViewById(R.id.self_info_span2);
        sp2.setBackgroundColor(Color.rgb(205,197,191 ));
        View xib1=findViewById(R.id.self_info_span3);
        xib1.setBackgroundColor(Color.rgb(205,197,191 ));
        View xib2=findViewById(R.id.self_info_short_span1);
        xib2.setBackgroundColor(Color.rgb(205,197,191 ));*/
        headImage=(ImageView)findViewById(R.id.self_select_head_image);
        LinearLayout about_me_ll=(LinearLayout)findViewById(R.id.header_image_ll);
        about_me_ll.setOnClickListener(this);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        nicheng=(TextView)findViewById(R.id.self_info_nich_text);
        sexself=(TextView)findViewById(R.id.selfinfo_sex_text);

    }


    public void init(){
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText("修改资料");
        StringBuffer sb = read(HEAD_NAME);
        //Toast.makeText(selfinforActivity.this, sb+"", Toast.LENGTH_SHORT).show();
        try {
            Uri uri = Uri.parse(sb.toString());
            Bitmap bitmap = BitmapFactory.decodeStream
                    (getContentResolver()
                            .openInputStream(uri));
            headImage.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer nichsb = read(NAME_NAME);
        nicheng.setText(nichsb.toString());
        StringBuffer sexsb = read(SEX_NAME);
        sexself.setText(sexsb.toString());
        mAdressText = (TextView) findViewById(R.id.adress_receive);
        String[] str = read("receiver.txt").toString().split("##");
        mAdressText.setText(str[1]);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.header_image_ll:
                Intent intent=new Intent(selfinforActivity.this,takephotoActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.navigation_bar_back_button:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Log.d("mainActivity", "hello world!");
        // Toast.makeText(selfinforActivity.this, "hello world!", Toast.LENGTH_SHORT).show();
        if (requestCode == 1) {
            String imageUrl = data.getStringExtra("data");
            Toast.makeText(selfinforActivity.this, imageUrl, Toast.LENGTH_SHORT).show();
            save(HEAD_NAME,imageUrl);
            try {
                Uri uri = Uri.parse(imageUrl);
                Bitmap bitmap = BitmapFactory.decodeStream
                        (getContentResolver()
                                .openInputStream(uri));
                headImage.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                headtitleimage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }




    private void save(String filename,String data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(filename, Activity.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private StringBuffer read(String filename) {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(filename);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }
}
