package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.ScreenShot;
import com.jy.android.travel.model.MatrixImageView;
import com.jy.android.travel.R;
import com.jy.android.travel.adapter.SelectAdapter;
import com.jy.android.travel.model.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/10/1.
 */

public class DIYMoralActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView mImageView;
    private TextView mTitleText;
    private GridView mGridView;
    private MatrixImageView mDIYImage;
    Calendar calendar;
    int i;
    private int[] mImage = {R.drawable.test_zi_1,R.drawable.mpen_0,
            R.drawable.mpen_1,R.drawable.mpen_2, R.drawable.mpen_3};

    public static final String IMAGE_ID = "com.jy.android.travel.image_id";
    private int Image[] = {R.drawable.test_model1,R.drawable.test_model2,R.drawable.test_model3
            ,R.drawable.test_model4,R.drawable.test_model5,R.drawable.test_model6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diymoral);
        init();
        finView();
        setGridView();
    }

    private void finView() {
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        findViewById(R.id.delete_button).setOnClickListener(this);
        findViewById(R.id.save_button).setOnClickListener(this);
    }

    private void setGridView() {
        int size = mImage.length;
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size*(length+4)*density);
        int itemWidth = (int) (length*density);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(gridviewWidth,LinearLayout.LayoutParams.MATCH_PARENT);
        mGridView.setLayoutParams(params);
        mGridView.setColumnWidth(itemWidth);
        mGridView.setHorizontalSpacing(5);
        mGridView.setStretchMode(GridView.NO_STRETCH);
        mGridView.setNumColumns(size);
        SelectAdapter adapter = new SelectAdapter(this,getImage());
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(this);
    }

    private void init() {
        Intent intent = getIntent();
        String x = intent.getStringExtra(IMAGE_ID);
        i = Integer.parseInt(x);
        calendar = Calendar.getInstance();
        mImageView = (ImageView) findViewById(R.id.diymoral_image);
        mImageView.setImageResource(Image[i]);
        mGridView = (GridView) findViewById(R.id.moral_grid);
        mDIYImage = (MatrixImageView) findViewById(R.id.diy_image);
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.diy));
    }


    List<Text> getImage(){
        List<Text> Texts = new ArrayList<>();
        for (int i=0;i<mImage.length;i++){
            Texts.add(new Text(mImage[i]));
        }
        return Texts;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
                break;
            case R.id.delete_button:
                mDIYImage.setImageBitmap(null);
                break;
            case R.id.save_button:
                File destDir = new File("/sdcard/create");
                if (!destDir.exists()){
                    destDir.mkdirs();
                }
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String id = year+"-"+month+"-"+day+"-"+hour+":"+minute+":"+second;
                ScreenShot.shoot(DIYMoralActivity.this,id);
                save("head.txt",id+".png##");
                saveModelName("moralName.txt",i);
                finish();
                Toast.makeText(DIYMoralActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mDIYImage.setImageResource(mImage[i]);
    }

    private void save(String filename,String data) {
        try {
            FileOutputStream out = openFileOutput(filename, Context.MODE_APPEND);
            out.write(data.getBytes("UTF-8"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveModelName(String filename,int i) {
        try {
            FileOutputStream out = openFileOutput(filename, Context.MODE_APPEND);
            switch (i){
                case 0:
                    out.write("衣服模板##".getBytes("UTF-8"));
                    out.close();
                    break;
                case 1:
                    out.write("杯子模板##".getBytes("UTF-8"));
                    out.close();
                    break;
                case 2:
                    out.write("扇子模板##".getBytes("UTF-8"));
                    out.close();
                    break;
                case 3:
                    out.write("鼠标垫模板##".getBytes("UTF-8"));
                    out.close();
                    break;
                case 4:
                    out.write("灯笼模板##".getBytes("UTF-8"));
                    out.close();
                    break;
                case 5:
                    out.write("盘子模板##".getBytes("UTF-8"));
                    out.close();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
