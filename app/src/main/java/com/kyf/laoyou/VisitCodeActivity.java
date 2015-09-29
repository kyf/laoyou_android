package com.kyf.laoyou;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

public class VisitCodeActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    private Context mContext;

    private Button ok, createbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_code);

        setTitle(R.string.title_activity_visit_code);

        initView();
    }

    private void initView(){
        mContext = this;
        ok = (Button) findViewById(R.id.ok);
        createbt = (Button) findViewById(R.id.createbt);
        ok.setOnClickListener(this);
        createbt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.ok:
                String title = "提示框";
                String content = "仿iOS的同时支持Alert和ActionSheet模式,一行代码就可以进行弹窗.";
                new AlertView(title, content, null, null, new String[]{"确定", "取消"}, this, AlertView.Style.Alert, VisitCodeActivity.this).show();
                break;
            case R.id.createbt:
                Intent intent = new Intent(mContext, CreateActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(Object obj, int position){
        Toast.makeText(this, position + "", Toast.LENGTH_LONG).show();
    }


}
