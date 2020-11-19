package com.me.youtu_android.okhttp;

import android.telecom.Call;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;


public class MyOkhttpUtils {

    private static MyOkhttpUtils mMyOkhttpUtils;
    private KProgressHUD mKProgressHUD;

    public static MyOkhttpUtils getInstance() {
        if (mMyOkhttpUtils == null) {
            synchronized (MyOkhttpUtils.class) {
                if (mMyOkhttpUtils == null) {
                    mMyOkhttpUtils = new MyOkhttpUtils();
                }
            }
        }
        return mMyOkhttpUtils;
    }

    public void postNet(AppCompatActivity activity, String url, Map<String, String> params, boolean isShow, final OkCallBack okCallBack) {
        if (null != activity && !activity.isFinishing() && isShow)
            mKProgressHUD = ProgressHK.show(activity);
        OkHttpUtils
                .get()
                .url(url)
                .params(params)
                .addParams("client", "101")
                .addParams("version", "6.5")
                .tag(activity)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        if (mKProgressHUD != null) {
                            mKProgressHUD.dismiss();
                        }
                        if (okCallBack != null) {
                            okCallBack.onFailure(e);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (mKProgressHUD != null) {
                            mKProgressHUD.dismiss();
                        }
                        if (okCallBack != null) {
                            okCallBack.onResponse(response);
                        }
                    }
                });

    }

}
