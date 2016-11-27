package com.jy.android.travel.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.jy.android.travel.R;
import com.jy.android.travel.fragment.CreativeFragment;
import com.jy.android.travel.fragment.DIYFragmnet;
import com.jy.android.travel.fragment.me_Fragment;
import com.jy.android.travel.fragment.viepager_Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private Fragment mFragment;
    private FragmentManager fm;
    private ImageButton mTextShowButton;
    private ImageButton mDIYButton;
    private ImageButton mCreativeButton;
    private ImageButton mMyInfoButton;
    private TextView mTextShow;
    private TextView mDIYText;
    private TextView mCreativeText;
    private TextView mMyInfoText;
    int x=0;
    String hanzi[] = {"中","国","动", "伟", "大", "复", "兴"};
    private EditText mSearchEdit;
    private ListView mSearchResult;
    private TextView mNoInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findView();
        init();
        checkReciver();
    }

    private void checkReciver() {
        File file = new File("receiver.txt");
        if (file.exists()){

        }else{
            save("receiver.txt","刘小安##北京联合大学北四环东路97号");
        }
    }

    private void init() {
        fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(R.id.frame);
        mTextShow = (TextView) findViewById(R.id.text_show_text);
        mDIYText = (TextView) findViewById(R.id.diy_text);
        mCreativeText = (TextView) findViewById(R.id.creative_text);
        mMyInfoText = (TextView) findViewById(R.id.myinfo_text);
        mTextShowButton = (ImageButton) findViewById(R.id.text_show_button);
        mDIYButton = (ImageButton) findViewById(R.id.diy_button);
        mCreativeButton = (ImageButton) findViewById(R.id.creative_button);
        mMyInfoButton = (ImageButton) findViewById(R.id.myinfo_button);
        mSearchEdit = (EditText) findViewById(R.id.search_edit);
        mSearchResult = (ListView) findViewById(R.id.search_result);
        mNoInfo = (TextView) findViewById(R.id.no_info);
        viepager_Fragment viepager_fragment = new viepager_Fragment();
        loadFragment(viepager_fragment);
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                findText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, font_content_Activity.class);
                Bundle bund=new Bundle();
                bund.putInt("itemkey",x);
                intent.putExtras(bund);
                startActivity(intent);
            }
        });

    }

    private void findText() {
        for (x=0;x<hanzi.length;x++){
            if (hanzi[x].equals(mSearchEdit.getText().toString())){
                String s1 = hanzi[x];
                List<String> s = new ArrayList<>();
                s.add(s1);
                mSearchResult.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,s));
                mSearchResult.setVisibility(View.VISIBLE);
                mNoInfo.setVisibility(View.GONE);
                return;
            }else{
                if(mSearchEdit.getText().toString().length()<1){
                    mNoInfo.setVisibility(View.GONE);
                }else{
                    mNoInfo.setVisibility(View.VISIBLE);
                }
                mSearchResult.setVisibility(View.GONE);
            }
        }
    }

    private void findView() {
        findViewById(R.id.creative_button).setOnClickListener(this);
        findViewById(R.id.diy_button).setOnClickListener(this);
        findViewById(R.id.myinfo_button).setOnClickListener(this);
        findViewById(R.id.text_show_button).setOnClickListener(this);
        findViewById(R.id.search_cancel_text).setOnClickListener(this);
        findViewById(R.id.search_image).setOnClickListener(this);
    }

    //加载相应的Fragment页面
    private void loadFragment(Fragment fragment) {
        if (mFragment == null) {
            mFragment = fragment;
            fm.beginTransaction().add(R.id.frame, mFragment).commit();
        }else{
            fm.beginTransaction().replace(R.id.frame,fragment).commit();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.creative_button:
                CreativeFragment creativeFragment = new CreativeFragment();
                loadFragment(creativeFragment);
                mCreativeText.setTextColor(this.getResources().getColor(R.color.colorMain));
                mDIYText.setTextColor(this.getResources().getColor(R.color.colorText));
                mMyInfoText.setTextColor(this.getResources().getColor(R.color.colorText));
                mTextShow.setTextColor(this.getResources().getColor(R.color.colorText));
                mTextShowButton.setBackgroundResource(R.drawable.ic_2_normal);
                mCreativeButton.setBackgroundResource(R.drawable.ic_4_selected);
                mDIYButton.setBackgroundResource(R.drawable.ic_3_normal);
                mMyInfoButton.setBackgroundResource(R.drawable.ic_1_normal);
                break;
            case R.id.diy_button:
                DIYFragmnet messageFragment = new DIYFragmnet();
                loadFragment(messageFragment);
                mDIYText.setTextColor(this.getResources().getColor(R.color.colorMain));
                mCreativeText.setTextColor(this.getResources().getColor(R.color.colorText));
                mMyInfoText.setTextColor(this.getResources().getColor(R.color.colorText));
                mTextShow.setTextColor(this.getResources().getColor(R.color.colorText));
                mTextShowButton.setBackgroundResource(R.drawable.ic_2_normal);
                mDIYButton.setBackgroundResource(R.drawable.ic_3_selected);
                mCreativeButton.setBackgroundResource(R.drawable.ic_4_normal);
                mMyInfoButton.setBackgroundResource(R.drawable.ic_1_normal);
                break;
            case R.id.text_show_button:
                viepager_Fragment viepager_fragment = new viepager_Fragment();
                loadFragment(viepager_fragment);
                mDIYText.setTextColor(this.getResources().getColor(R.color.colorText));
                mCreativeText.setTextColor(this.getResources().getColor(R.color.colorText));
                mMyInfoText.setTextColor(this.getResources().getColor(R.color.colorText));
                mTextShow.setTextColor(this.getResources().getColor(R.color.colorMain));
                mTextShowButton.setBackgroundResource(R.drawable.ic_2_selected);
                mDIYButton.setBackgroundResource(R.drawable.ic_3_normal);
                mCreativeButton.setBackgroundResource(R.drawable.ic_4_normal);
                mMyInfoButton.setBackgroundResource(R.drawable.ic_1_normal);
                break;
            case R.id.myinfo_button:
                me_Fragment me_fragment = new me_Fragment();
                loadFragment(me_fragment);
                mMyInfoText.setTextColor(this.getResources().getColor(R.color.colorMain));
                mCreativeText.setTextColor(this.getResources().getColor(R.color.colorText));
                mDIYText.setTextColor(this.getResources().getColor(R.color.colorText));
                mTextShow.setTextColor(this.getResources().getColor(R.color.colorText));
                mTextShowButton.setBackgroundResource(R.drawable.ic_2_normal);
                mMyInfoButton.setBackgroundResource(R.drawable.ic_1_selected);
                mDIYButton.setBackgroundResource(R.drawable.ic_3_normal);
                mCreativeButton.setBackgroundResource(R.drawable.ic_4_normal);
                break;
            case R.id.search_cancel_text:
                mSearchEdit.setText("");
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
