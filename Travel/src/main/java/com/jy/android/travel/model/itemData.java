package com.jy.android.travel.model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 乖宝宝 on 2016/9/30.
 */
public class itemData {
    private int imageId;
    private String title;
    private String yuyi;
    public itemData(int i,String t,String y){
        this.imageId=i;
        this.title=t;
        this.yuyi=y;
    }
    public int getImageId(){
        return this.imageId;
    }
    public String getTitle(){
        return this.title;
    }
    public String getYuyi(){
        return this.yuyi;
    }
}
