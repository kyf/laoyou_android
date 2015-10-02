package com.kyf.laoyou;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CreateActivity extends BaseActivity implements View.OnClickListener {

    private Button create_next_step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_create;
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(){
        create_next_step = (Button) findViewById(R.id.create_next_step);
        create_next_step.setOnClickListener(this);
    }

    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.create_next_step:
                Intent intent = new Intent(this, TouchPwdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
