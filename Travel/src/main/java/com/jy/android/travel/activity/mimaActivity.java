package com.jy.android.travel.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.android.travel.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class mimaActivity extends Activity implements View.OnClickListener {
    private Button nextbtn;
    private TextView mTitleText;
    private EditText firstmima,secondmima;
    private final static String SECURATE_NAME = "mima.txt"; // 设置文件的名称
   // private SQLiteDataOpt sql=new SQLiteDataOpt();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mima_activity);
        nextbtn=(Button)findViewById(R.id.mima_next_step);
        firstmima=(EditText)findViewById(R.id.first_mima);
        secondmima=(EditText)findViewById(R.id.second_mima);
        findViewById(R.id.navigation_bar_back_button).setOnClickListener(this);
        mTitleText = (TextView) findViewById(R.id.navigation_bar_title_text);
        mTitleText.setText(this.getResources().getText(R.string.set_password));
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_str=firstmima.getText().toString();
                String second_str=secondmima.getText().toString();
                if(second_str.equals(first_str)&&!first_str.equals(""))
                {
                    save(SECURATE_NAME,second_str);
                    Intent intent=new Intent(mimaActivity.this,registerSuccessActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(mimaActivity.this, "密码不对!", Toast.LENGTH_SHORT).show();
                    return;
                }
               // sql.save(SECURATE_NAME,second_str);

            }
        });
    }


    private void save(String filename,String data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(filename, Activity.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigation_bar_back_button:
                finish();
                break;
        }
    }
}
