package com.kyf.laoyou;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class SwipeCodeActivity extends BaseActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private QRCodeReaderView mydecoderview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_code);

        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        mydecoderview.getCameraManager().closeDriver();
        Toast.makeText(SwipeCodeActivity.this, text, Toast.LENGTH_SHORT).show();
    }


    // Called when your device have no camera
    @Override
    public void cameraNotFound() {
        Toast.makeText(SwipeCodeActivity.this, "camera  not found", Toast.LENGTH_SHORT).show();
    }

    // Called when there's no QR codes in the camera preview image
    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }

}
