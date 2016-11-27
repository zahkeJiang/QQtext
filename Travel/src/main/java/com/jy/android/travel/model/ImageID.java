package com.jy.android.travel.model;

import android.graphics.Bitmap;

import java.util.UUID;

/**
 * Created by Administrator on 2016/10/3.
 */

public class ImageID {
    public Bitmap mBitmap;
    public String mDate;
    public String mModel;
    public ImageID(Bitmap bitmap,String date,String model){
        mBitmap = bitmap;
        mDate = date;
        mModel = model;
    }
}
