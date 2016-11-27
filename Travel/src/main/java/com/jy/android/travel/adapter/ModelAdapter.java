package com.jy.android.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.android.travel.CoreUtil;
import com.jy.android.travel.R;
import com.jy.android.travel.model.Stencil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/1.
 */

public class ModelAdapter extends BaseAdapter {
    private List<Stencil> mStencils;
    private LayoutInflater mInflater;

    public ModelAdapter(Context context, List<Stencil> mStencils){
        mInflater = LayoutInflater.from(context);
        this.mStencils = mStencils;
    }
    @Override
    public int getCount() {
        return mStencils.size();
    }

    @Override
    public Object getItem(int i) {
        return mStencils.get(i);
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
            view = mInflater.inflate(R.layout.common_diy,null);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.image_view);
            viewHolder.mModelText = (TextView) view.findViewById(R.id.text_view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Stencil stencil = mStencils.get(i);
        viewHolder.mModelText.setText(stencil.mStencil);
        //CoreUtil.loadImage(viewHolder.mImageView,0,0,0,stencil.mImageId,null);
        viewHolder.mImageView.setImageResource(stencil.mImageId);
        return view;
    }

    public class ViewHolder{
        public ImageView mImageView;
        public TextView mModelText;
    }
}
