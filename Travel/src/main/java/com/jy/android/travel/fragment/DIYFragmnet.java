package com.jy.android.travel.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jy.android.travel.activity.DIYMoralActivity;
import com.jy.android.travel.adapter.ModelAdapter;
import com.jy.android.travel.R;
import com.jy.android.travel.model.Stencil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */

public class DIYFragmnet extends Fragment {
    private GridView mGridView;
    Resources resources;
    private int Image[] = {R.drawable.test_model1,R.drawable.test_model2,R.drawable.test_model3
            ,R.drawable.test_model4,R.drawable.test_model5,R.drawable.test_model6};
    private int stencil[] = {R.string.test_model1,R.string.test_model2,R.string.test_model3,
            R.string.test_model4,R.string.test_model5,R.string.test_model6};
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diy,container,false);
        resources = inflater.getContext().getResources();
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mGridView.setAdapter(new ModelAdapter(inflater.getContext(),LoadData()));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(inflater.getContext(), DIYMoralActivity.class);
                intent.putExtra(DIYMoralActivity.IMAGE_ID,i+"");
                startActivity(intent);
                //Toast.makeText(inflater.getContext(),""+i,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public List<Stencil> LoadData(){
        List<Stencil> stencils = new ArrayList<>();
        for (int i =0;i<6;i++){
            stencils.add(new Stencil(Image[i],this.getResources().getString(stencil[i])));
        }
        return stencils;
    }
}
