package com.kyf.laoyou;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends BaseActivity {

    private static final int ROUTER_STATE_DEFAULT = 1001;

    private static final int ROUTER_STATE_LOGINED = 1002;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        mLayout = R.layout.activity_main;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                int state = router();
                switch (state) {
                    case ROUTER_STATE_DEFAULT:
                        break;
                    case ROUTER_STATE_LOGINED:
                        intent.setClass(MainActivity.this, VisitCodeActivity.class);
                        break;
                }
                startActivity(intent);
                MainActivity.this.finish();
            }
        }, 1000);
    }

    private int router(){
        //islogin

        //default
        return ROUTER_STATE_LOGINED;
    }

}
