package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class registerSuccessActivity extends Activity implements View.OnClickListener {
    private final static String HEAD_NAME="head.txt";
    private final static String NAME_NAME="nichen.txt";
    private final static String SEX_NAME="sex.txt";
    private ImageView head;
    private TextView mTitleText;
    private Button finish;
    private EditText nich;
    private boolean ischoice=false;
  //  private SQLiteDataOpt sql=new SQLiteDataOpt();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registersuccess_activity);
        finish=(Button)findViewById(R.id.register_finish);
        nich=(EditText)findViewById(R.id.in_nichen);
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(R.string.register_sucess);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        head=(ImageView)findViewById(R.id.register_get_image);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registerSuccessActivity.this,takephotoActivity.class);
                startActivityForResult(intent,2);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nich.getText().toString().equals("")){
                    Toast.makeText(registerSuccessActivity.this, "请输入昵称!", Toast.LENGTH_SHORT).show();
                    return ;
                }
                else
                {
                    String nichdata=nich.getText().toString();
                //    sql.save(NAME_NAME,nichdata);
                    save(NAME_NAME,nichdata);
                }
                RadioGroup sex=(RadioGroup)findViewById(R.id.select_sex);
                for(int i=0;i<sex.getChildCount();i++){
                    RadioButton select=(RadioButton)sex.getChildAt(i);
                    if(select.isChecked()){
                    //    sql.save(SEX_NAME,select.getText().toString());
                        save(SEX_NAME,select.getText().toString());
                        ischoice=true;
                        finish();
                        break;
                    }
                }
                if(ischoice)
                {
                   // Intent sueecess=new Intent(registerSuccessActivity.this,RegisterDenluActivity.class);
                    //startActivity(sueecess);
                    //finish();
                }
            }
        });
        init();
    }


    public void init(){
        StringBuffer sb = read(HEAD_NAME);
        if(sb.equals("")){
            head.setImageResource(R.drawable.ic_zihui);
        }
        else {
            //Toast.makeText(.this, sb+"", Toast.LENGTH_SHORT).show();
            try {
                Uri uri = Uri.parse(sb.toString());
                Bitmap bitmap = BitmapFactory.decodeStream
                        (getContentResolver()
                                .openInputStream(uri));
                head.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                head.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            String imageUrl = data.getStringExtra("data");
            Toast.makeText(registerSuccessActivity.this, imageUrl, Toast.LENGTH_SHORT).show();
         //   sql.save(HEAD_NAME,imageUrl);
            save(HEAD_NAME,imageUrl);
            Toast.makeText(registerSuccessActivity.this, "保存成功!", Toast.LENGTH_SHORT).show();
            try {
                Uri uri = Uri.parse(imageUrl);
                Bitmap bitmap = BitmapFactory.decodeStream
                        (getContentResolver()
                                .openInputStream(uri));
                head.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
                break;
        }
    }
}
