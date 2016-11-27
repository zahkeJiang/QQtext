package com.jy.android.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jy.android.travel.R;
import com.jy.android.travel.adapter.CreateAdapter;
import com.jy.android.travel.activity.ShopActivity;
import com.jy.android.travel.model.Creator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */

public class CreativeFragment extends Fragment {
    private ListView mListView;
    private int image[] = {R.drawable.test_fu, R.drawable.test_ma,
            R.drawable.test_fudiao,R.drawable.test_minsu};

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creative,container,false);
        mListView = (ListView) view.findViewById(R.id.creative_list);
        mListView.setAdapter(new CreateAdapter(this.getContext(),LoadData()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(inflater.getContext(), ShopActivity.class);
                intent.putExtra(ShopActivity.SHOPTEXT,i+"");
                startActivity(intent);
            }
        });
        return view;
    }

    public List<Creator> LoadData(){
        List<Creator> creators = new ArrayList<>();
        String[] text1 = this.getResources().getString(R.string.fu).split("##");
        String[] text2 = this.getResources().getString(R.string.fudiao).split("##");
        String[] text3 = this.getResources().getString(R.string.ma).split("##");
        String[] text4 = this.getResources().getString(R.string.minsu).split("##");
        creators.add(new Creator(image[0],text1[0], text1[1],text1[2]));
        creators.add(new Creator(image[1],text2[0],text2[1],text2[2]));
        creators.add(new Creator(image[2],text3[0],text3[1],text3[2]));
        creators.add(new Creator(image[3],text4[0],text4[1],text4[2]));
        return creators;
    }

}
