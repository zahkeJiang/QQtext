package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.android.travel.R;
import com.jy.android.travel.model.ShopText;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/2.
 */

public class ShopActivity extends Activity implements View.OnClickListener {
    //private ListView mCardList;
    private ImageView mShopImage;
    private TextView mShopTitle;
    private TextView mShopPrice;
    private TextView mSizeContent;
    private TextView mMoralContent;
    int i;
    public static final String SHOPTEXT = "com.jy.android.shop";
    private int image[] = {R.drawable.test_fu,R.drawable.test_ma,
            R.drawable.test_fudiao,R.drawable.test_minsu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        findView();
        init();
        //save("receiver.txt","刘小安##北京联合大学北四环东路97号");
        //CardAdapter adapter = new CardAdapter(this,getShopText());
        //mCardList.setAdapter(adapter);
        setShopText();
    }

    private void findView() {
        findViewById(R.id.shop_button).setOnClickListener(this);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
    }

    private void init() {
        mShopImage = (ImageView) findViewById(R.id.image_view_shop);
        //mCardList = (ListView) findViewById(R.id.card_list);
        mShopTitle = (TextView) findViewById(R.id.shop_title);
        mShopPrice = (TextView) findViewById(R.id.shop_price);
        mSizeContent = (TextView) findViewById(R.id.size_content);
        mMoralContent = (TextView) findViewById(R.id.moral_content);
    }

    List<ShopText> setShopText(){
        List<ShopText> shopTexts = new ArrayList<>();
        Intent intent = getIntent();
         i = Integer.parseInt(intent.getStringExtra(SHOPTEXT));
        switch (i){
            case 0:
                String[] text1 = this.getResources().getString(R.string.fu).split("##");
                mSizeContent.setText(text1[3]);
                mMoralContent.setText(text1[1]);
                mShopImage.setImageResource(image[0]);
                mShopTitle.setText(text1[0]);
                mShopPrice.setText(text1[2]);
                break;
            case 1:
                String[] text2 = this.getResources().getString(R.string.fudiao).split("##");
                mSizeContent.setText(text2[3]);
                mMoralContent.setText(text2[1]);
                mShopImage.setImageResource(image[1]);
                mShopTitle.setText(text2[0]);
                mShopPrice.setText(text2[2]);

                break;
            case 2:
                String[] text3 = this.getResources().getString(R.string.ma).split("##");
                mSizeContent.setText(text3[3]);
                mMoralContent.setText(text3[1]);
                mShopImage.setImageResource(image[2]);
                mShopTitle.setText(text3[0]);
                mShopPrice.setText(text3[2]);
                break;
            case 3:
                String[] text4 = this.getResources().getString(R.string.minsu).split("##");
                mSizeContent.setText(text4[3]);
                mMoralContent.setText(text4[1]);
                mShopImage.setImageResource(image[3]);
                mShopTitle.setText(text4[0]);
                mShopPrice.setText(text4[2]);
                break;
        }
        return shopTexts;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shop_button:
                Intent intent = new Intent(ShopActivity.this,BuyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("image",i+"");
                bundle.putString("title",mShopTitle.getText().toString());
                bundle.putString("size",mSizeContent.getText().toString());
                bundle.putString("price",mShopPrice.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.navigation_bar_back_button:
                finish();
                break;
        }
    }
    private void save(String filename,String data) {
        try {
            FileOutputStream out = openFileOutput(filename, Context.MODE_PRIVATE);
            out.write(data.getBytes("UTF-8"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}