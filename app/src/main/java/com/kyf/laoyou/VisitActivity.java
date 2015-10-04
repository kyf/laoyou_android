package com.kyf.laoyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class VisitActivity extends BaseActivity implements View.OnClickListener {

    private Button SwipeCodeBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_visit;
        super.onCreate(savedInstanceState);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView(){
        SwipeCodeBt = (Button) findViewById(R.id.SwipeCodeBt);

        SwipeCodeBt.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.SwipeCodeBt:{
                Intent intent = new Intent(this, QRCodeActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
