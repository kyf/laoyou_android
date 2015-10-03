package com.kyf.laoyou;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.yoojia.zxing.FinderView;
import com.github.yoojia.zxing.QRCodeScanSupport;

public class SwipeCodeActivity extends BaseActivity implements QRCodeScanSupport.OnScanResultListener {

    private QRCodeScanSupport mQRCodeScanSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLayout = R.layout.activity_swipe_code;
        super.onCreate(savedInstanceState);

        /*
        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);
        */


        // 查找布局文件中的元素
        ImageView capturePreview = (ImageView) findViewById(R.id.decode_preview);
        final FinderView finderView = (FinderView) findViewById(R.id.capture_viewfinder_view);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.capture_preview_view);

        // 创建扫描支持类
        mQRCodeScanSupport = new QRCodeScanSupport(surfaceView, finderView);
        //mQRCodeScanSupport.setCapturePreview(capturePreview);

        // 如何处理扫描结果
        mQRCodeScanSupport.setOnScanResultListener(this);
    }

    @Override
    public void onScanResult(String notNullResult) {
        Intent intent = new Intent();
        intent.putExtra("qrcode", notNullResult);
        setResult(1001, intent);
        this.finish();
    }

    @Override
    protected void onResume() {
        mQRCodeScanSupport.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mQRCodeScanSupport.onPause(this);
        super.onPause();
    }

}
