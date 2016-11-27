package com.jy.android.travel.model;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 乖宝宝 on 2016/10/1.
 */
public class textWordsData extends Activity{
    //public String str[]=new String[7];
    public List<String> list;
    //private static  int i=0;
    public textWordsData(){
        list=new ArrayList<String>();
    }
    public void setList(String s){
        list.add(s);
    }
   /* public String[] getStr(){
        return str;
    }*/
}

