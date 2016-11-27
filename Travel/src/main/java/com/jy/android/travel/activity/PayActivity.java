package com.jy.android.travel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jy.android.travel.R;

/**
 * Created by Administrator on 2016/10/2.
 */

public class PayActivity extends Activity implements View.OnClickListener {
    private TextView mTitleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_act);
        findView();
        init();
    }

    private void init() {
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.pay_text));
    }

    private void findView() {
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
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
