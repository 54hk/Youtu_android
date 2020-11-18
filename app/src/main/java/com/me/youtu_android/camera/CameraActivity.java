package com.me.youtu_android.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.me.youtu_android.R;

import java.util.List;

public class CameraActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    ImageView imageView;
    Button butPicture;
    private SurfaceHolder mHolder;

    private Camera.Parameters mCParameters;
    private Camera mCamera;

    private static SparseIntArray sparseIntArray = new SparseIntArray();

    static {
        sparseIntArray.append(Surface.ROTATION_0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        surfaceView = findViewById(R.id.surfaceView);
        imageView = findViewById(R.id.imageView);
        butPicture = findViewById(R.id.but_picture);

        mHolder = surfaceView.getHolder();
        mHolder.setFixedSize(177, 144);
        mHolder.setKeepScreenOn(true);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.addCallback(new MyCallBack());

        butPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        if (data.length > 0) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        });
    }

    class MyCallBack implements SurfaceHolder.Callback {


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(0);
            mCamera.startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mCParameters = mCamera.getParameters();
            List<Camera.Size> supportedPictureSizes = mCParameters.getSupportedPictureSizes();
            if (supportedPictureSizes.isEmpty()) {
                mCParameters.setPreviewSize(width, height);
            } else {
                Camera.Size size = supportedPictureSizes.get(0);
                mCParameters.setPreviewSize(size.width, size.height);
            }
            mCParameters.setPictureFormat(PixelFormat.JPEG);
            mCParameters.setPreviewSize(width, height);
            mCParameters.setJpegQuality(80);
            mCParameters.setPreviewFrameRate(5);

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mCamera != null) {
                mCamera.release();
                mCamera = null;
            }
        }
    }

//    public int getRotation(Activity activity) {
//        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
//        switch (rotation) {
//        }
//
//    }rotation

    public int indexOf(String main, String sub) {

        char[] mainStr = main.toCharArray();
        char[] subStr = sub.toCharArray();
        int mainStrLength = mainStr.length;
        int subStrLength = subStr.length;
        for (int i = 0; i < mainStrLength; i++) {
            int sum = 0;
            if (mainStr[i] == subStr[0]) {
                for (int ii = 0; ii < subStrLength; ii++) {
                    if (mainStr[i + ii] == subStr[ii]) {
                        sum++;
                    }
                }
            }
            if (sum == subStrLength)
                return i;
        }
        return -1;
    }

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    public ListNode addTwoNumber(ListNode l1 , ListNode l2){
        return new ListNode(setNum(l1.val) + setNum(l1.val));
    }

    public int setNum(int ii){
        StringBuffer sb = new StringBuffer();
        char[] chars = String.valueOf(ii).toCharArray();
        for (int i = chars.length-1 ; i >=0 ; i --){
            sb.append(chars[i]);
        }
        return Integer.valueOf(sb.toString());
    }

















}
