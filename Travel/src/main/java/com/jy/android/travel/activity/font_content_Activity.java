package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.android.travel.R;
import com.jy.android.travel.model.textWordsData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class font_content_Activity extends Activity implements View.OnClickListener{
   // private static int i=0;
    private TextView font_title,font_duyin,font_history,font_yuyi,font_tedian;
    private ImageView font_image;
    textWordsData td=new textWordsData();
    private String[] strInfo=new String[8];
    private int i=0;
    //textWordsData textword=new textWordsData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.font_content_layout);
        findView();
        //init();
        Intent intent=getIntent();
        Bundle bund=intent.getExtras();
        int item=bund.getInt("itemkey");
        Log.d("font_content_Activity",item+"");
        System.out.println(item+"");
        getRaw();
        getrawStrings(item);
    }

    public void findView(){
        font_title=(TextView)findViewById(R.id.font_content_title);
        font_duyin=(TextView)findViewById(R.id.font_content_duyin);
        font_history=(TextView)findViewById(R.id.font_content_history);
        font_yuyi=(TextView)findViewById(R.id.font_content_yuyi);
        font_tedian=(TextView)findViewById(R.id.font_content_tedian);
        font_image=(ImageView)findViewById(R.id.font_content_image);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
    }

    /*public void init(){
        View view_tone=findViewById(R.id.color_tone);
        view_tone.setBackgroundColor(Color.rgb(255,215,0  ));
        View view_tone1=findViewById(R.id.color_tone1);
        view_tone1.setBackgroundColor(Color.rgb(255,215,0 ));
        View view_tone2=findViewById(R.id.color_tone2);
        view_tone2.setBackgroundColor(Color.rgb(255,215,0 ));
        View view_tone4=findViewById(R.id.color_tone4);
        view_tone4.setBackgroundColor(Color.rgb(255,215,0  ));
        View view_tone5=findViewById(R.id.color_tone5);
        view_tone5.setBackgroundColor(Color.rgb(255,215,0  ));
        View sp1=findViewById(R.id.spinbar_1);
        sp1.setBackgroundColor(Color.rgb(211,211,211));
        View sp2=findViewById(R.id.spinbar_2);
        sp2.setBackgroundColor(Color.rgb(211,211,211));
        View sp3=findViewById(R.id.spinbar_3);
        sp3.setBackgroundColor(Color.rgb(211,211,211));
        View sp4=findViewById(R.id.spinbar_4);
        sp4.setBackgroundColor(Color.rgb(211,211,211));
        View sp5=findViewById(R.id.spinbar_5);
        sp5.setBackgroundColor(Color.rgb(211,211,211));
    }*/

    public void getrawStrings(int k){
        switch(k){
            case 0:
                String strinfors0[]=td.list.get(0).split("##");
                font_title.setText(strinfors0[0].toString());
                font_duyin.setText(strinfors0[1].toString());
                font_history.setText(strinfors0[2].toString());
                //strinfors0[3]=strinfors0[3].replace("。","\n");
                String shi[]=strinfors0[3].split("。");
                shi[0]+="。\n";shi[1]+="。\n";
                font_yuyi.setText(shi[0]+shi[1]);
                strinfors0[4]=strinfors0[4].replace("；","\n");
                font_tedian.setText(strinfors0[4].toString());
                font_image.setImageResource(R.drawable.test_zhong);
                break;

            case 1:
                String strinfors1[]=td.list.get(1).split("##");
                font_title.setText(strinfors1[0].toString());
                font_duyin.setText(strinfors1[1].toString());
                font_history.setText(strinfors1[2].toString());
                String shi1[]=strinfors1[3].split("。");
                shi1[0]+="。\n";shi1[1]+="。\n";
                font_yuyi.setText(shi1[0]+shi1[1]);
                strinfors1[4]=strinfors1[4].replace("；","\n");
                font_tedian.setText(strinfors1[4].toString());
                font_image.setImageResource(R.drawable.test_guo);
                break;

            case 2:
                String strinfors2[]=td.list.get(2).split("##");
                font_title.setText(strinfors2[0].toString());
                font_duyin.setText(strinfors2[1].toString());
                font_history.setText(strinfors2[2].toString());
                String shi2[]=strinfors2[3].split("。");
                shi2[0]+="。\n";shi2[1]+="。\n";
                font_yuyi.setText(shi2[0]+shi2[1]);
                strinfors2[4]=strinfors2[4].replace("；","\n");
                font_tedian.setText(strinfors2[4].toString());
                font_image.setImageResource(R.drawable.test_dong);
                break;
            case 3:
                String strinfors3[]=td.list.get(3).split("##");
                font_title.setText(strinfors3[0].toString());
                font_duyin.setText(strinfors3[1].toString());
                font_history.setText(strinfors3[2].toString());
                String shi3[]=strinfors3[3].split("。");
                shi3[0]+="。\n";shi3[1]+="。\n";
                font_yuyi.setText(shi3[0]+shi3[1]);
                strinfors3[4]=strinfors3[4].replace("；","\n");
                font_tedian.setText(strinfors3[4].toString());
                font_image.setImageResource(R.drawable.test_wei);
                break;
            case 4:
                String strinfors4[]=td.list.get(4).split("##");
                font_title.setText(strinfors4[0].toString());
                font_duyin.setText(strinfors4[1].toString());
                font_history.setText(strinfors4[2].toString());
                String shi4[]=strinfors4[3].split("。");
                shi4[0]+="。\n";shi4[1]+="。\n";
                font_yuyi.setText(shi4[0]+shi4[1]);
                strinfors4[4]=strinfors4[4].replace("；","\n");
                font_tedian.setText(strinfors4[4].toString());
                font_image.setImageResource(R.drawable.test_da);
                break;
            case 5:
                String strinfors5[]=td.list.get(5).split("##");
                font_title.setText(strinfors5[0].toString());
                font_duyin.setText(strinfors5[1].toString());
                font_history.setText(strinfors5[2].toString());
                String shi5[]=strinfors5[3].split("。");
                shi5[0]+="。\n";shi5[1]+="。\n";
                font_yuyi.setText(shi5[0]+shi5[1]);
                strinfors5[4]=strinfors5[4].replace("；","\n");
                font_tedian.setText(strinfors5[4].toString());
                font_image.setImageResource(R.drawable.test_fu);
                break;
            case 6:
                String strinfors6[]=td.list.get(6).split("##");
                font_title.setText(strinfors6[0].toString());
                font_duyin.setText(strinfors6[1].toString());
                font_history.setText(strinfors6[2].toString());
                String shi6[]=strinfors6[3].split("。");
                shi6[0]+="。\n";shi6[1]+="。\n";
                font_yuyi.setText(shi6[0]+shi6[1]);
                strinfors6[4]=strinfors6[4].replace("；","\n");
                font_tedian.setText(strinfors6[4].toString());
                font_image.setImageResource(R.drawable.test_xing);
                break;
            default:
                break;
        }
    }

    public void getRaw(){
            try {
                InputStream inputStream = getResources().openRawResource(R.raw.test_words);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while ((strInfo[i] = bufferedReader.readLine()) != null) {
                    td.setList(strInfo[i]);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
            default:
                break;
        }
    }
    // Log.d("font_content",strInfo[0]);


}
