package com.kyf.laoyou;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.kyf.laoyou.view.MyLoading;

public class VisitCodeActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    private Context mContext;

    private Button ok, createbt;

    private MyLoading myLoading;

    private EditText visit_code_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_code);

        initView();
    }

    private void initView(){
        mContext = this;
        ok = (Button) findViewById(R.id.ok);
        createbt = (Button) findViewById(R.id.createbt);
        visit_code_text = (EditText) findViewById(R.id.visit_code_text);
        ok.setOnClickListener(this);
        createbt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.ok:
                final String visitCode = visit_code_text.getText().toString().trim();
                if(visitCode.equals("")){
                    String title = "提示";
                    String content = mContext.getResources().getString(R.string.visitcode_hint);
                    new AlertView(title, content, null, null, new String[]{"确定"}, this, AlertView.Style.Alert, VisitCodeActivity.this).show();
                    return;
                }
                myLoading = new MyLoading(this);
                myLoading.setCanceledOnTouchOutside(false);
                myLoading.setContent(getResources().getString(R.string.tip_activity_visit_code));
                myLoading.show();

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.putExtra("visitCode", visitCode);
                        intent.setClass(mContext, HomeActivity.class);
                        mContext.startActivity(intent);
                        myLoading.dismiss();
                        ((AppCompatActivity)mContext).finish();
                    }
                }, 1000);
                break;
            case R.id.createbt:
                Intent intent = new Intent(mContext, CreateActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(Object obj, int position){
        //Toast.makeText(this, position + "", Toast.LENGTH_LONG).show();
    }


}
