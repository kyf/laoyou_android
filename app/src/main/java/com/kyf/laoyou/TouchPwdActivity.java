package com.kyf.laoyou;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.eftimoff.patternview.PatternView;
import com.kyf.laoyou.view.MyLoading;

public class TouchPwdActivity extends AppCompatActivity {

    private static final String LogTag = "TouchPwdActivity****";

    private PatternView patternView;

    private String patternString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pwd);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();
        /*
        MyLoading dialog = new MyLoading(this);
        dialog.setCanceledOnTouchOutside(false);
        //dialog.setContent("正在加载...");
        dialog.show();
        */
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


    private void initView() {
        patternView = (PatternView) findViewById(R.id.patternView);
        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {
            @Override
            public void onPatternDetected() {
                if (patternString == null) {
                    patternString = patternView.getPatternString();
                    patternView.clearPattern();
                    return;
                }
                if (patternString.equals(patternView.getPatternString())) {
                    Toast.makeText(getApplicationContext(), "PATTERN CORRECT" + patternString, Toast.LENGTH_SHORT).show();
                    patternView.clearPattern();
                    return;
                }
                Toast.makeText(getApplicationContext(), "PATTERN NOT CORRECT", Toast.LENGTH_SHORT).show();
                patternView.clearPattern();

            }
        });
    }

}
