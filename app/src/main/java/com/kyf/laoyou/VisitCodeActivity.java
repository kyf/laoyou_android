package com.kyf.laoyou;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
//import android.widget.PopupMenu;
import android.support.v7.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.kyf.laoyou.view.MyLoading;

public class VisitCodeActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener {

    private Context mContext;

    private Button ok, createbt;

    private MyLoading myLoading;

    private EditText visit_code_text;

    private ImageView visit_code_more_bt;

    private PopupWindow pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_visit_code;
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView(){
        mContext = this;
        ok = (Button) findViewById(R.id.ok);
        createbt = (Button) findViewById(R.id.createbt);
        visit_code_text = (EditText) findViewById(R.id.visit_code_text);
        ok.setOnClickListener(this);
        createbt.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.menu_visit);
        actionBar.setDisplayShowCustomEnabled(true);
        visit_code_more_bt = (ImageView) actionBar.getCustomView().findViewById(R.id.visit_code_more_bt);
        visit_code_more_bt.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(resultCode){
            case 1001:{
                String qrcode = data.getStringExtra("qrcode");
                if(qrcode != null) {
                    visit_code_text.setText(qrcode);
                    Toast.makeText(this, qrcode, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getResources().getString(R.string.read_qrcode_failure), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }



    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.menu_swipe_bt:{
                Intent intent = new Intent(this, SwipeCodeActivity.class);
                startActivityForResult(intent, 1001);
                pw.dismiss();
                break;
            }
            case R.id.visit_code_more_bt:{
                if(pw == null){
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View menuList = inflater.inflate(R.layout.menu_visit_list, null);
                    TextView menu_swipe_bt = (TextView) menuList.findViewById(R.id.menu_swipe_bt);
                    menu_swipe_bt.setOnClickListener(this);
                    pw = new PopupWindow(menuList, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    pw.setBackgroundDrawable(new BitmapDrawable());
                    pw.showAsDropDown(view);
                }else{
                    if(pw.isShowing()){
                        pw.dismiss();
                    }else {
                        pw.showAsDropDown(view);
                    }
                }
                break;
            }
            case R.id.ok:
                final String visitCode = visit_code_text.getText().toString().trim();
                if(visitCode.equals("")){
                    String title = "提示";
                    String content = mContext.getResources().getString(R.string.visitcode_hint);
                    alertView = new AlertView(title, content, null, null, new String[]{"确定"}, this, AlertView.Style.Alert, VisitCodeActivity.this);
                    alertView.show();
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
