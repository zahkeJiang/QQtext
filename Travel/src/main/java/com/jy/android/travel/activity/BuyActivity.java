package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.android.travel.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/10/2.
 */

public class BuyActivity extends Activity implements View.OnClickListener {
    private TextView mTitleText;
    private ImageView mCommodityImage;
    private TextView mCommodityTitle;
    private TextView mCommoditySize;
    private TextView mCommodityPrice;
    private TextView mBuyPrice;
    private TextView mAllPrice;
    private TextView mBuyName;
    private TextView mBuyAdress;

    private int image[] = {R.drawable.test_fu,R.drawable.test_fudiao,
            R.drawable.test_ma,R.drawable.test_minsu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_act);
        findView();
        init();
    }

    private void init() {
        Intent intent = getIntent();
        int i = Integer.parseInt(intent.getStringExtra("image"));
        String title = intent.getStringExtra("title");
        String size = intent.getStringExtra("size");
        String price = intent.getStringExtra("price");
        float p = Float.parseFloat(price)+10;
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.fill_order));
        mCommodityImage = (ImageView) findViewById(R.id.commodity_image);
        mCommodityImage.setImageResource(image[i]);
        mCommodityTitle = (TextView) findViewById(R.id.commodity_title);
        mCommodityTitle.setText(title);
        mCommoditySize = (TextView) findViewById(R.id.commodity_size);
        mCommoditySize.setText(this.getResources().getText(R.string.size)+size);
        mCommodityPrice = (TextView) findViewById(R.id.commodity_price);
        mCommodityPrice.setText(price);
        mBuyPrice = (TextView) findViewById(R.id.buy_price);
        mBuyPrice.setText(price);
        mAllPrice = (TextView) findViewById(R.id.all_price);
        mAllPrice.setText(this.getResources().getText(R.string.real_pay)+""+p);
        mBuyName = (TextView) findViewById(R.id.buy_name);
        mBuyAdress = (TextView) findViewById(R.id.buy_address);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String[] str = read("receiver.txt").toString().split("##");
        mBuyName.setText(str[0]);
        mBuyAdress.setText(str[1]);
    }

    private void findView() {
        findViewById(R.id.buy_button).setOnClickListener(this);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        findViewById(R.id.set_adress).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buy_button:
                Intent intent = new Intent(BuyActivity.this,PayActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_bar_back_button:
                finish();
                break;
            case R.id.set_adress:
                Intent intent1 = new Intent(BuyActivity.this,SetAdressActivity.class);
                startActivity(intent1);
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
}
