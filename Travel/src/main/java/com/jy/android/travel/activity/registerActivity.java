package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;
import com.jy.android.travel.SMSContentObserver;
import com.thinkland.sdk.sms.SMSCaptcha;
import com.thinkland.sdk.util.BaseData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class registerActivity extends Activity implements View.OnClickListener {
    private final static String Account_NAME="phone.txt";
    private Button nextbtn;
    private TextView mTitleText;
    private EditText phonenum,yzhma_msg;
    private Button Recceive;
   // private SQLiteDataOpt sql=new SQLiteDataOpt();
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == 1)
            {
                yzhma_msg.setText(msg.obj.toString());
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register_activity);
        nextbtn=(Button)findViewById(R.id.first_next_step);
        final SMSCaptcha smscapt= SMSCaptcha.getInstance();//创建SMSCaptcha对象
        phonenum=(EditText)findViewById(R.id.register_phonenumber);
        yzhma_msg=(EditText)findViewById(R.id.register_yzhma);
        Recceive=(Button)findViewById(R.id.register_get_yzhma);
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.register));
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone_str=yzhma_msg.getText().toString().trim();//获取输入的验证码并去除字符串的头部和尾部的空格符号
                smscapt.commitCaptcha(phonenum.getText().toString(), phone_str, new BaseData.ResultCallBack() {
                    @Override
                    public void onResult(int code, String reason, String result) {
                        //Toast.makeText(registerActivity.this, reason, Toast.LENGTH_SHORT).show();
                        if(code==0){
                            //验证码正确的操作
                            save(Account_NAME,phonenum.getText().toString());
                            Intent intent=new Intent(registerActivity.this,mimaActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            //验证码错误的操作
                        }
                    }
                });
                //String acount=phonenum.getText().toString();
               // sql.save(Account_NAME,acount);
               // save(Account_NAME,acount);
            }
        });
        Recceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smscapt.sendCaptcha(phonenum.getText().toString(), new BaseData.ResultCallBack(){
                    @Override
                    public void onResult(int code, String reason, String result) {
                        //Toast.makeText(registerActivity.this, reason, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        SMSContentObserver smsContentObserver = new SMSContentObserver(
                registerActivity.this, handler);
        registerActivity.this.getContentResolver().registerContentObserver(
                Uri.parse("content://sms/"), true, smsContentObserver);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
                break;
        }
    }
}
