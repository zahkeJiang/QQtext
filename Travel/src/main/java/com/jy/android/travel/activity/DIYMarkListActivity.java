package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;
import com.jy.android.travel.adapter.DiyJiLuAdapter;
import com.jy.android.travel.model.ImageID;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/10/3.
 */

public class DIYMarkListActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private String filepath = "sdcard/create/";
    private ListView mListView;
    private TextView mTitleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diylist);
        init();
        findView();
        DiyJiLuAdapter adapter = new DiyJiLuAdapter(this,getImage());
        mListView.setAdapter(adapter);
    }

    private void findView() {
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.diy_image_list);
        mListView.setOnItemClickListener(this);
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.diyjilu));
    }
    public List<ImageID> getImage() {
        List<ImageID> imageIDs = new ArrayList<>();
        StringBuffer sb = read("head.txt");
        String str = sb.toString();
        String[] strings = str.split("##");
        StringBuffer mn = readModelName("moralName.txt");
        String strmn = mn.toString();
        String[] stringsmn = strmn.split("##");
        if (strings.length < 1) {
            Toast.makeText(DIYMarkListActivity.this, "没有记录", Toast.LENGTH_SHORT).show();
            finish();
        }
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            String m = stringsmn[i];
            File mFile = new File(filepath+s);
            //若该文件存在
            if (mFile.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(filepath + s);
                imageIDs.add(new ImageID(bitmap, s.split(".png")[0],m));
                //Toast.makeText(DIYMarkListActivity.this, "存在", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DIYMarkListActivity.this, "不存在", Toast.LENGTH_SHORT).show();
            }
        }
        return imageIDs;
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
    private StringBuffer readModelName(String filename) {
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
    private void delete(String filename,String data) {
        try {
            FileOutputStream out = openFileOutput(filename, Context.MODE_PRIVATE);
            out.write(data.getBytes("UTF-8"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(DIYMarkListActivity.this,DIYShowActivity.class);
        intent.putExtra("diyiamge",i+"");
        startActivity(intent);
    }
}
