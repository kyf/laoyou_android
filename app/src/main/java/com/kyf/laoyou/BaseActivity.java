package com.kyf.laoyou;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.bigkoo.alertview.AlertView;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String LogTag = "BaseActivity";

    protected int mLayout = 0;

    protected AlertView alertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(mLayout != 0) {
            setContentView(mLayout);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent ev){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(alertView != null && alertView.isShowing()){
                alertView.dismiss();
                return true;
            }
        }

        return super.onKeyDown(keyCode, ev);
    }
}
