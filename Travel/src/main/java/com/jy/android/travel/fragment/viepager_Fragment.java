package com.jy.android.travel.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jy.android.travel.R;
import com.jy.android.travel.activity.MainActivity;
import com.jy.android.travel.activity.font_content_Activity;
import com.jy.android.travel.adapter.itemAdapter;
import com.jy.android.travel.model.itemData;
import com.jy.android.travel.adapter.pagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class viepager_Fragment extends Fragment implements ViewPager.OnPageChangeListener,View.OnTouchListener,AdapterView.OnItemClickListener {

    private ViewPager viewpager;
    private pagerAdapter adapter;
    private List<View> listview;
    private List<itemData> list;
    private EditText mSearchEdit;
    private int ImageId[]={R.id.iv1,R.id.iv2,R.id.iv3};
    private ImageView imageview[];
    private int index=0;
    private boolean isTouch=false;
    private String[] strInfo=new String[7];
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewpager.setCurrentItem(index);
        }
    };

    public viepager_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_viepager_, container, false);
        mSearchEdit = (EditText) view.findViewById(R.id.search_edit);
        listview=new ArrayList<View>();//viewpager的list
        list=new ArrayList<itemData>();//ListView的list
        viewpager=(ViewPager)view.findViewById(R.id.view_pager);
        //view.findViewById(R.id.search_cancel_text).setOnClickListener(this);
        adapter=new pagerAdapter(listview,getActivity());
        ListView list_item_view=(ListView)view.findViewById(R.id.list_view);
        itemAdapter listview_adapter=new itemAdapter(getActivity(),R.layout.list_view_item,list);
        init();//初始化viewpager
        initData();//初始化ListView
        viewpager.setAdapter(adapter);
        list_item_view.setAdapter(listview_adapter);
        setListViewHeightBasedOnChildren(list_item_view);
        list_item_view.setOnItemClickListener(this);
        viewpager.setOnPageChangeListener(this);
        new Thread(){
            @Override
            public void run() {
                while (true ){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(1);
                    if(!isTouch) {
                        index++;
                        if (index > listview.size()) {
                            index = 0;
                        }
                    }
                }
            }
        }.start();
        //getRaw();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        imageview=new ImageView[listview.size()];
        for(int i=0;i<listview.size();i++){
            imageview[i]=(ImageView)getView().findViewById(ImageId[i]);
        }
    }

    public void init(){
        LayoutInflater inflater=LayoutInflater.from(getActivity());
        listview.add(inflater.inflate(R.layout.one,null));
        listview.add(inflater.inflate(R.layout.two,null));
        listview.add(inflater.inflate(R.layout.three,null));
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        index=position;
        for(int i=0;i<ImageId.length;i++){
            if(i==position){
                imageview[i].setImageResource(R.drawable.login_point_selected);
            }
            else
            {
                imageview[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                isTouch=false;
                break;
            case MotionEvent.ACTION_MOVE:
                isTouch=true;
                break;
        }
        return false;
    }
    public void initData(){
        itemData i1=new itemData(R.drawable.test_zhong,"中 中","中字，从口从竖。它用“口”的字形，来表示东南西北四个方向，即“口呈四方”；用一竖来确定中央的位置。“四方既定，可得中央”。");
        list.add(i1);
        itemData i2=new itemData(R.drawable.test_guo,"國  国  国","國字，從域從口。域，代表地面區域；口，代表牧場的圉邊，即後來的國防綫。");
        list.add(i2);
        itemData i3=new itemData(R.drawable.test_dong,"動  动  动","動（动）字，从重从力。有重力情况下，才会出现动。");
        list.add(i3);
        itemData i4=new itemData(R.drawable.test_wei,"偉  伟  伟","偉（伟）字，从人从韋（韦）。人，即一个站立起来的人；韦，中间一口，两步绕着走，说明在这个人的身边，集聚了许多人，大家都以他为中心，围绕着他转。");
        list.add(i4);
        itemData i5=new itemData(R.drawable.test_da,"大  大","大字，从一从人。一为先天地生的道；人为一撇为阳，一捺为阴的后天的“一阴一阳之为道”。");
        list.add(i5);
        itemData i6=new itemData(R.drawable.test_fu_zi,"復  复  复","復（复）字，从彳指行走和循环；从复，上面是个人字，下面是个日字，底下是个处。指“人过日子”，日复一日，年复一年，处于一种循环的重复现象。");
        list.add(i6);
        itemData i7=new itemData(R.drawable.test_xing,"興  兴  兴","興（兴）字，从同从学（學）从共。老同学（學）共同聚会在一起，心情高兴（興）。");
        list.add(i7);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), font_content_Activity.class);
                Bundle bund=new Bundle();
                bund.putInt("itemkey",i);
                intent.putExtras(bund);
                startActivity(intent);
    }
/*
    public void getRaw(){
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.test_words);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((strInfo[i] = bufferedReader.readLine()) != null) {
                textword.setList(strInfo[i]);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Log.d("font_content",strInfo[0]);
    }*/



    private void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


   /* @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_cancel_text:
                mSearchEdit.setText("");
                mSearchEdit.clearFocus();
                break;
        }
    }*/

}


