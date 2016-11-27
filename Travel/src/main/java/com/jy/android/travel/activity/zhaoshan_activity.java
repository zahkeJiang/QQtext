package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jy.android.travel.R;

public class zhaoshan_activity extends Activity implements View.OnClickListener {
    private TextView mTitleText;
    private Button phone_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_zhaoshan_activity);
        findView();
        init();
        final String phone=(phone_btn.getText().toString()).substring(5);
        phone_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });
    }

    private void findView() {
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
    }

    private void init() {
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.zhaoshanjiaming));
        phone_btn=(Button)findViewById(R.id.phone_btn);
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
