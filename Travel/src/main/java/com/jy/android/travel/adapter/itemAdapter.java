package com.jy.android.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.android.travel.R;
import com.jy.android.travel.model.itemData;

import java.util.List;

/**
 * Created by 乖宝宝 on 2016/9/30.
 */
public class itemAdapter extends ArrayAdapter<itemData>{
    private int resouceId;
    public itemAdapter(Context context, int textViewResourceId,
                       List<itemData> objects) {
        super(context,textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.resouceId=textViewResourceId;//将子项的布局id给保存起来
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         itemData id=getItem(position);//获得fruit实例
        View view;
        ViewHandle vhold=new ViewHandle();
        if(convertView==null)
        {
            view= LayoutInflater.from(getContext()).inflate(resouceId, null);
            vhold.imageview=(ImageView)view.findViewById(R.id.image_view);
            vhold.titlename=(TextView)view.findViewById(R.id.text_title);
            vhold.yuyicontent=(TextView)view.findViewById(R.id.text_yuyi);
            view.setTag(vhold);
        }
        else
        {
            view=convertView;
            vhold=(ViewHandle)view.getTag();
        }
        vhold.imageview.setImageResource(id.getImageId());
        vhold.titlename.setText(id.getTitle());
        vhold.yuyicontent.setText(id.getYuyi());
        return view;
    }
    class ViewHandle
    {
        ImageView imageview;
        TextView titlename;
        TextView yuyicontent;
    }

}
