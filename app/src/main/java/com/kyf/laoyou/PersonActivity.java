package com.kyf.laoyou;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kyf.laoyou.view.MyLoading;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonActivity extends BaseActivity implements View.OnClickListener {

    private String nickname, phone, weixin, qq;

    private int photo;

    private TextView phone_view, wx_view, qq_view;

    private CircleImageView person_avatar;

    private MyLoading myLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_person;
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        photo = intent.getIntExtra("photo", R.mipmap.avatar_default);
        phone = intent.getStringExtra("phone");
        weixin = intent.getStringExtra("weixin");
        qq = intent.getStringExtra("qq");

        setTitle(nickname);
        initView();
    }

    private void initView(){
        person_avatar = (CircleImageView) findViewById(R.id.person_avatar);
        phone_view = (TextView) findViewById(R.id.phone_view);
        wx_view = (TextView) findViewById(R.id.wx_view);
        qq_view = (TextView) findViewById(R.id.qq_view);
        person_avatar.setImageResource(photo);
        phone_view.setText(phone);
        qq_view.setText(qq);
        wx_view.setText(weixin);
        phone_view.setOnClickListener(this);
        qq_view.setOnClickListener(this);
        wx_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.phone_view:{
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ((TextView)view).getText().toString()));
                startActivity(intent);
                break;
            }
            case R.id.wx_view:{
                myLoading = new MyLoading(this);
                myLoading.setCanceledOnTouchOutside(true);
                myLoading.setContent(getResources().getString(R.string.loading_boot_wx));
                myLoading.show();
                Intent intent = new Intent();
                ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cmp);
                break;
            }
            case R.id.qq_view:{
                myLoading = new MyLoading(this);
                myLoading.setCanceledOnTouchOutside(true);
                myLoading.setContent(getResources().getString(R.string.loading_boot_qq));
                myLoading.show();
                String url="mqqwpa://im/chat?chat_type=wpa&uin=" + ((TextView)view).getText().toString();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            }
        }
    }

    @Override
    public void onPause(){
        if(myLoading != null && myLoading.isShowing()) {
            myLoading.dismiss();
        }
        super.onPause();
    }

    @Override
    public void onStop(){
        if(myLoading != null && myLoading.isShowing()) {
            myLoading.dismiss();
        }
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:{
                finish();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
