package com.kyf.laoyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VisitActivity extends BaseActivity implements View.OnClickListener {

    private Button SwipeCodeBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_visit;
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView(){
        SwipeCodeBt = (Button) findViewById(R.id.SwipeCodeBt);

        SwipeCodeBt.setOnClickListener(this);
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
