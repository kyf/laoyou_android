package com.kyf.laoyou;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

public class PubActivity extends BaseActivity {

    private static final String LogTag = "PubActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_pub;
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Log.e(LogTag, id + "");
        switch(id){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
