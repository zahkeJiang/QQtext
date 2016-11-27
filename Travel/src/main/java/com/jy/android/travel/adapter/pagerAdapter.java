package com.jy.android.travel.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by 乖宝宝 on 2016/9/28.
 */
public class pagerAdapter extends PagerAdapter{
    private Context context;
    private List<View> list;
    public pagerAdapter(List<View> list,Context c) {
        this.context=c;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager)container).addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(list.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}
