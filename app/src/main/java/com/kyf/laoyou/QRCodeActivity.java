package com.kyf.laoyou;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.yoojia.zxing.QRCodeEncode;
import com.kyf.laoyou.view.MyLoading;

public class QRCodeActivity extends BaseActivity implements Runnable {

    private static final String LogTag = "QRCodeActivity";

    private MyLoading myLoading;

    private ImageView qrcode_image;

    private static final Integer HANDLER_STATE_COMPLETE_QRCODE = 1001;

    private Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            if(msg.what == HANDLER_STATE_COMPLETE_QRCODE){
                qrcode_image.setImageBitmap((Bitmap)msg.obj);
                qrcode_image.setVisibility(View.VISIBLE);
                myLoading.dismiss();
                return;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_qrcode;
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        qrcode_image = (ImageView) findViewById(R.id.qrcode_image);

        myLoading = new MyLoading(this);
        myLoading.setCanceledOnTouchOutside(false);
        myLoading.setContent(getResources().getString(R.string.create_qrcode));
        myLoading.show();

        new Thread(this).start();
    }

    @Override
    public void run(){
        final int dimension = 500;
        final QRCodeEncode encoder = new QRCodeEncode.Builder()
                .setBackgroundColor(0xFFFFFF) // 指定背景颜色，默认为白色
                .setCodeColor(0xFF000000) // 指定编码块颜色，默认为黑色
                .setOutputBitmapWidth(dimension) // 生成图片宽度
                .setOutputBitmapHeight(dimension) // 生成图片高度
                //.setOutputBitmapMargin(0) // 设置为没有白边
                .build();

        String code = "kekfjhf";
        final Bitmap _QRCodeImage = encoder.encode(code);
        Message msg = Message.obtain();
        msg.what = HANDLER_STATE_COMPLETE_QRCODE;
        msg.obj = _QRCodeImage;
        myHandler.sendMessage(msg);
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
}
