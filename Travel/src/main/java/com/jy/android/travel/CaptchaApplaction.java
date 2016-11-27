package com.jy.android.travel;

import android.app.Application;

import com.thinkland.sdk.util.CommonFun;

/**
 * Created by 乖宝宝 on 2016/10/3.
 */
public class CaptchaApplaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonFun.initialize(getApplicationContext(),false);
    }
}

