package com.jy.android.travel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jy.android.travel.R;

/**
 * Created by Administrator on 2016/10/3.
 */

public class BaseActivity extends Activity {
    private ImageButton mBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBackButton = (ImageButton) findViewById(R.id.navigation_bar_back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
