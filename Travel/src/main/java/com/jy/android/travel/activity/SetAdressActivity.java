package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jy.android.travel.R;

import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/10/3.
 */

public class SetAdressActivity extends Activity implements View.OnClickListener {

    private TextView mTitleText;
    private EditText mReciverName;
    private EditText mReciverAdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setadress);
        findView();
        init();
    }

    private void findView() {
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        findViewById(R.id.keep_adress).setOnClickListener(this);
    }

    private void init() {
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.change_adress));
        mReciverAdress = (EditText) findViewById(R.id.reciver_adress);
        mReciverName = (EditText) findViewById(R.id.reciver_name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
                break;
            case R.id.keep_adress:
                save("receiver.txt",mReciverName.getText().toString()+
                        "##"+mReciverAdress.getText().toString());
                finish();
                break;
        }
    }
    private void save(String filename,String data) {
        try {
            FileOutputStream out = openFileOutput(filename, Context.MODE_PRIVATE);
            out.write(data.getBytes("UTF-8"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
