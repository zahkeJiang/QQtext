package com.jy.android.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.android.travel.R;
import com.jy.android.travel.model.Stencil;
import com.jy.android.travel.model.Text;

import java.util.List;

/**
 * Created by Administrator on 2016/10/1.
 */

public class SelectAdapter extends BaseAdapter{
    private List<Text> mTexts;
    private LayoutInflater mInflater;

    public SelectAdapter(Context context, List<Text> mTexts){
        mInflater = LayoutInflater.from(context);
        this.mTexts = mTexts;
    }
    @Override
    public int getCount() {
        return mTexts.size();
    }

    @Override
    public Object getItem(int i) {
        return mTexts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.select_image,null);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.select_image_view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Text text = mTexts.get(i);
        viewHolder.mImageView.setImageResource(text.mImage);
        return view;
    }

    public class ViewHolder{
        public ImageView mImageView;
    }
}
