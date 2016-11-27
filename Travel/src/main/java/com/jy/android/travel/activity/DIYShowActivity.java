package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.jy.android.travel.R;
import com.jy.android.travel.model.MatrixImageViewBig;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/10/3.
 */

public class DIYShowActivity extends Activity {
    private String filepath = "sdcard/create/";
    int i;
    private MatrixImageViewBig mDIYShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyshow);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        i = Integer.parseInt(intent.getStringExtra("diyiamge"));
        StringBuffer sb = read("head.txt");
        String str = sb.toString();
        String[] strings = str.split("##");
        mDIYShow = (MatrixImageViewBig) findViewById(R.id.diy_image_show);
        File mFile = new File(filepath+strings[i]);
        //若该文件存在
        if (mFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(filepath + strings[i]);
            mDIYShow.setImageBitmap(bitmap);
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
