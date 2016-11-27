package com.jy.android.travel.activity;

import android.app.Activity;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class forgetActivity extends Activity implements View.OnClickListener{
    private EditText forget_num,forget_y,forget_x;
    private Button forget_Btn,forget_receiver;
    private TextView mTitleText;
    private final static String Account_NAME="phone.txt";
    private final static String SECURATE_NAME = "mima.txt"; // 设置文件的名称
    final SMSCaptcha smscapt= SMSCaptcha.getInstance();//创建SMSCaptcha对象
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == 1)
            {
                forget_y.setText(msg.obj.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forget_activity);
        findview();
        SMSContentObserver smsContentObserver = new SMSContentObserver(
                forgetActivity.this, handler);

        forgetActivity.this.getContentResolver().registerContentObserver(
                Uri.parse("content://sms/"), true, smsContentObserver);

    }
    public void findview(){
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.forget_password));
        forget_num=(EditText)findViewById(R.id.forget_phone);
        forget_y=(EditText)findViewById(R.id.forget_yzhma);
        forget_x=(EditText)findViewById(R.id.forget_reinput_mima);
        forget_receiver=(Button)findViewById(R.id.forget_get_yzhma);
        forget_receiver.setOnClickListener(this);
        forget_Btn=(Button)findViewById(R.id.forget_yzh_btn);
        forget_Btn.setOnClickListener(this);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.forget_yzh_btn:
                String phone=forget_num.getText().toString();
                String yzhma_text=forget_y.getText().toString();
                String xinmima=forget_x.getText().toString();
                if(!phone.equals("")&&!yzhma_text.equals("")&&!xinmima.equals(""))
                {
                    Toast.makeText(forgetActivity.this, "验证成功!", Toast.LENGTH_SHORT).show();
                    save(Account_NAME,phone);
                    save(SECURATE_NAME,xinmima);
                    finish();
                }
                break;
            case R.id.forget_get_yzhma:
                smscapt.sendCaptcha(forget_num.getText().toString(), new BaseData.ResultCallBack() {//使用SMSCaptcha类的方法sendCaptcha实现发送短信
                    @Override
                    public void onResult(int code, String reason, String result) {
                        Toast.makeText(forgetActivity.this, reason, Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.navigation_bar_back_button:
                finish();
                break;
            default:
                break;
        }
    }

    private void save(String filename, String data) {
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
