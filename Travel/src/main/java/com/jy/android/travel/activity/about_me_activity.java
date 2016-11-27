package com.jy.android.travel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jy.android.travel.R;

public class about_me_activity extends Activity implements View.OnClickListener {
    private TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about_me_activity);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        init();
    }

    private void init() {
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.about_me));
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
