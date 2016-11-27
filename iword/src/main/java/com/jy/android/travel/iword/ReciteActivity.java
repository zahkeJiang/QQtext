package com.jy.android.travel.iword;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/10/7.
 */

public class ReciteActivity extends Activity implements View.OnClickListener {
    private TextView mText1;
    private TextView mText2;
    private String[] strInfo=new String[8];
    String strinfors[] = new String[74];
    private int i=0;
    int x = 0;
    textWordsData td=new textWordsData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);
        findView();
        init();
    }

    private void init() {
        getRaw();
        getrawStrings();
        mText1 = (TextView) findViewById(R.id.text1);
        mText2 = (TextView) findViewById(R.id.text2);
        mText1.setText(strinfors[x].split("-")[0]);
    }

    private void findView() {
        findViewById(R.id.check_btn).setOnClickListener(this);
        findViewById(R.id.next_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.check_btn:
                mText2.setText(strinfors[x].split("-")[1]);
                    mText2.setVisibility(View.VISIBLE);
                break;
            case R.id.next_btn:
                x++;
                mText1.setText(strinfors[x].split("-")[0]);
                    mText2.setVisibility(View.GONE);
                break;
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

    private void save(String filename,String data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(filename, Activity.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void getRaw(){
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.word);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((strInfo[i] = bufferedReader.readLine()) != null) {
                td.setList(strInfo[i]);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getrawStrings(){
        strinfors=td.list.get(0).split("##");
    }
}
