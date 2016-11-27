package com.jy.android.travel.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;
import com.jy.android.travel.activity.DIYMarkListActivity;
import com.jy.android.travel.activity.RegisterDenluActivity;
import com.jy.android.travel.activity.about_me_activity;
import com.jy.android.travel.activity.selfinforActivity;
import com.jy.android.travel.activity.zhaoshan_activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 */
public class me_Fragment extends Fragment implements View.OnClickListener{
    private View view;
    private final static String HEAD_NAME="head.txt";
    private final static String NAME_NAME="nichen.txt";
    private ImageView headimage;
    private TextView nichtext;
    private Button submit;
    private static Button exit;
    private static boolean isdenlu=false;
    int x = 0;
    public me_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_me_, container, false);
        //init();
        findview();
        return view;
    }

    public void findview(){
        LinearLayout about_me_ll=(LinearLayout)view.findViewById(R.id.about_me_click);
        about_me_ll.setOnClickListener(this);
        LinearLayout zhao_shan_ll=(LinearLayout)view.findViewById(R.id.zhao_shan_click);
        zhao_shan_ll.setOnClickListener(this);
        LinearLayout self_infor_ll=(LinearLayout)view.findViewById(R.id.selef_infor_click);
        self_infor_ll.setOnClickListener(this);
        LinearLayout diy_jilu_ll=(LinearLayout)view.findViewById(R.id.diy_jilu_click);
        diy_jilu_ll.setOnClickListener(this);
        submit=(Button) view.findViewById(R.id.please_submit);
        submit.setOnClickListener(this);
        exit=(Button)view.findViewById(R.id.exit_du);
        exit.setOnClickListener(this);
        headimage=(ImageView)view.findViewById(R.id.denglu_scccess_head_image);
        nichtext=(TextView)view.findViewById(R.id.denglu_scccess_nichen_text);
        StringBuffer nichsb = read(NAME_NAME);
        StringBuffer sb = read(HEAD_NAME);
        if(nichsb!=null&&sb!=null&&x == 1)
        {
            submit.setVisibility(View.GONE);
            headimage.setVisibility(View.VISIBLE);
            nichtext.setVisibility(View.VISIBLE);
            try {
                Uri uri = Uri.parse(sb.toString());
                Bitmap bitmap = BitmapFactory.decodeStream
                        (getActivity().getContentResolver()
                                .openInputStream(uri));
                headimage.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            nichtext.setText(nichsb.toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        StringBuffer sb = read(HEAD_NAME);
        if(isdenlu)
        {
            submit.setVisibility(View.GONE);
            headimage.setVisibility(View.VISIBLE);
            nichtext.setVisibility(View.VISIBLE);
            //StringBuffer sb = read(HEAD_NAME);
            //Toast.makeText(getActivity(), sb + "", Toast.LENGTH_SHORT).show();
            if (sb.length()>0){try {
                Uri uri = Uri.parse(sb.toString());
                Bitmap bitmap = BitmapFactory.decodeStream
                        (getActivity().getContentResolver()
                                .openInputStream(uri));
                headimage.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                }
            }
            StringBuffer nichsb = read(NAME_NAME);
            nichtext.setText(nichsb.toString());
            exit.setText("退出登录");
            exit.setVisibility(View.VISIBLE);
            exit.setEnabled(true);
        }
        else
        {
            submit.setVisibility(View.VISIBLE);
            headimage.setVisibility(View.GONE);
            nichtext.setVisibility(View.GONE);
            exit.setText("");
            exit.setVisibility(View.GONE);
            exit.setEnabled(false);
        }
    }

   /* public void init(){
        View sp1=view.findViewById(R.id.me_content_frag_span);
        sp1.setBackgroundColor(Color.rgb(205,197,191 ));
        View sp2=view.findViewById(R.id.me_content_frag_span2);
        sp2.setBackgroundColor(Color.rgb(205,197,191 ));
        View xib1=view.findViewById(R.id.me_content_frag_xibai);
        xib1.setBackgroundColor(Color.rgb(205,197,191 ));
        View xib2=view.findViewById(R.id.me_content_frag_xibai2);
        xib2.setBackgroundColor(Color.rgb(205,197,191 ));
    }*/

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.about_me_click:
                Intent intent=new Intent(getActivity(),about_me_activity.class);
                startActivity(intent);
                break;
            case R.id.zhao_shan_click:
                Intent zhaoshan=new Intent(getActivity(),zhaoshan_activity.class);
                startActivity(zhaoshan);
                break;
            case R.id.selef_infor_click:
                Intent selfinfo=new Intent(getActivity(),selfinforActivity.class);
                startActivity(selfinfo);
                break;
            case R.id.diy_jilu_click:
                Intent diyjilu = new Intent(getActivity(), DIYMarkListActivity.class);
                startActivity(diyjilu);
                break;
            case R.id.please_submit:
                Intent submit1=new Intent(getActivity(),RegisterDenluActivity.class);
                startActivityForResult(submit1,112);
                break;
            case R.id.exit_du:
                submit.setVisibility(View.VISIBLE);
                headimage.setVisibility(View.GONE);
                nichtext.setVisibility(View.GONE);
                exit.setText("");
                exit.setEnabled(false);
                exit.setVisibility(View.GONE);
                //bool=false;
                isdenlu=false;
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1) {
            x = 1;
            submit.setVisibility(View.GONE);
            headimage.setVisibility(View.VISIBLE);
            nichtext.setVisibility(View.VISIBLE);
            StringBuffer sb = read(HEAD_NAME);
            //Toast.makeText(getActivity(), sb + "", Toast.LENGTH_SHORT).show();
            if (sb != null){
                try {
                    Uri uri = Uri.parse(sb.toString());
                    Bitmap bitmap = BitmapFactory.decodeStream
                            (getActivity().getContentResolver()
                                    .openInputStream(uri));
                    headimage.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            StringBuffer nichsb = read(NAME_NAME);
            nichtext.setText(nichsb.toString());
            exit.setText("退出登录");
            exit.setVisibility(View.VISIBLE);
            exit.setEnabled(true);
            isdenlu=true;
        }
    }

    private StringBuffer read(String filename) {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = getActivity().openFileInput(filename);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

}
