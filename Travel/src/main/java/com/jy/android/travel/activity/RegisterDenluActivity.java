package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;

import java.io.FileInputStream;
import java.util.Scanner;

public class RegisterDenluActivity extends Activity implements View.OnClickListener{
    private TextView forget_text,register_text;
    private Button submit;
    private TextView mTitleText;
    private EditText Dphonenumber,Dmima;
    private StringBuffer namesb,securet;
    int x = 0;
    private final static String Account_NAME="phone.txt";
    private final static String SECURATE_NAME = "mima.txt"; // 设置文件的名称
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registerdenlu_activity);
        findView();
        forget_text=(TextView)findViewById(R.id.forget);
        forget_text.setOnClickListener(this);
        register_text=(TextView)findViewById(R.id.register);
        register_text.setOnClickListener(this);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
        Dphonenumber=(EditText)findViewById(R.id.denlu_phone);
        Dmima=(EditText)findViewById(R.id.denlu_mima);
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.login));
        namesb=read(Account_NAME);
        securet=read(SECURATE_NAME);
        Dphonenumber.setText(namesb.toString());
        Dmima.setText(securet.toString());
    }

    private void findView() {
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        StringBuffer namesb=read(Account_NAME);
        StringBuffer securet=read(SECURATE_NAME);
        Dphonenumber.setText(namesb.toString());
        Dmima.setText(securet.toString());
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
                break;
            case R.id.forget:
                Intent intent=new Intent(RegisterDenluActivity.this,forgetActivity.class);
                startActivity(intent);
            //Toast.makeText(RegisterDenluActivity.this, "hello world", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register:
                Intent newacount=new Intent(RegisterDenluActivity.this,registerActivity.class);
                startActivity(newacount);
                break;
            case R.id.submit:
                namesb=read(Account_NAME);
                securet=read(SECURATE_NAME);
                String accountstr=Dphonenumber.getText().toString();
                String mima=Dmima.getText().toString();
                //StringBuffer namesb=read(Account_NAME);
                //StringBuffer mimasb=read(SECURATE_NAME);
                if(accountstr.equals(namesb.toString())&&mima.equals(securet.toString())&&!accountstr.equals("")&&!mima.equals(""))
                {
                    Toast.makeText(RegisterDenluActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();
                    Intent back=new Intent();
                    x = 1;
                    setResult(x,back);
                    finish();
                }
                else
                {
                    x = 0;
                    Toast.makeText(RegisterDenluActivity.this, "账号或密码不正确!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
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
