package com.jy.android.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.android.travel.R;
import com.jy.android.travel.model.Creator;

import java.util.List;

/**
 * Created by Administrator on 2016/10/1.
 */

public class CreateAdapter extends BaseAdapter {
    private List<Creator> mCreators;
    private LayoutInflater mInflater;

    public CreateAdapter(Context context, List<Creator> mCreators){
        mInflater = LayoutInflater.from(context);
        this.mCreators = mCreators;
    }
    @Override
    public int getCount() {
        return mCreators.size();
    }

    @Override
    public Object getItem(int i) {
        return mCreators.get(i);
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
            view = mInflater.inflate(R.layout.common_creative,null);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.creative_image);
            viewHolder.mTitleText = (TextView) view.findViewById(R.id.title_text);
            viewHolder.mMoralText = (TextView) view.findViewById(R.id.moral_text);
            viewHolder.mPriceText = (TextView) view.findViewById(R.id.price_text);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Creator creator = mCreators.get(i);
        viewHolder.mTitleText.setText(creator.mTitle);
        viewHolder.mImageView.setImageResource(creator.mImage);
        viewHolder.mMoralText.setText(creator.mMoral);
        viewHolder.mPriceText.setText(creator.mPrice);
        return view;
    }

    class ViewHolder{
        public ImageView mImageView;
        public TextView mTitleText;
        public TextView mMoralText;
        public TextView mPriceText;
    }
}
