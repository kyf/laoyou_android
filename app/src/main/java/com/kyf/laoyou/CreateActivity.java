package com.kyf.laoyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private Button create_next_step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        this.setTitle(R.string.title_activity_create);

        initView();
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
