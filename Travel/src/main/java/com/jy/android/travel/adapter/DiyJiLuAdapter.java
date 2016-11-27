package com.jy.android.travel.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;
import com.jy.android.travel.activity.DIYMarkListActivity;
import com.jy.android.travel.model.ImageID;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/10/1.
 */

public class DiyJiLuAdapter extends BaseAdapter {
    private List<ImageID> mImageIDs;
    private LayoutInflater mInflater;

    public DiyJiLuAdapter(Context context, List<ImageID> imageIDs){
        mInflater = LayoutInflater.from(context);
        this.mImageIDs = imageIDs;
    }
    @Override
    public int getCount() {
        return mImageIDs.size();
    }

    @Override
    public Object getItem(int i) {
        return mImageIDs.get(i);
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
            view = mInflater.inflate(R.layout.list_diy_item,null);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.diy_list_image);
            viewHolder.mDateText = (TextView) view.findViewById(R.id.create_date);
            viewHolder.mModelName = (TextView) view.findViewById(R.id.create_image_id);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageID mImage = mImageIDs.get(i);
        viewHolder.mDateText.setText(mImage.mDate);
        viewHolder.mImageView.setImageBitmap(mImage.mBitmap);
        viewHolder.mModelName.setText(mImage.mModel);
        return view;
    }

    class ViewHolder{
        public ImageView mImageView;
        private TextView mDateText;
        private TextView mModelName;
    }
}
