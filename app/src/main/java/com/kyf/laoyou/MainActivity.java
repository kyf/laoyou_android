package com.kyf.laoyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends Activity {

    private static final int ROUTER_STATE_DEFAULT = 1001;

    private static final int ROUTER_STATE_LOGINED = 1002;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent();
                int state = router();
                switch(state){
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
