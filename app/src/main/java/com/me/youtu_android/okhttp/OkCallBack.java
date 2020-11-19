package com.me.youtu_android.okhttp;

public interface OkCallBack {
    void onFailure(Exception e);
    void onResponse(String data);
}
