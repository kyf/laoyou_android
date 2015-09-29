package com.kyf.laoyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.eftimoff.patternview.PatternView;
import com.kyf.laoyou.view.MyLoading;

public class TouchPwdActivity extends AppCompatActivity {

    private PatternView patternView;

    private String patternString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pwd);
        setTitle(R.string.title_activity_touch_pwd);


        initView();
        /*
        MyLoading dialog = new MyLoading(this);
        dialog.setCanceledOnTouchOutside(false);
        //dialog.setContent("正在加载...");
        dialog.show();
        */
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
