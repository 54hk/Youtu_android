package com.me.youtu_android.mediaplay;

import android.media.MediaPlayer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyMediaPlayer extends MediaPlayer implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPauseMedia(){
        pause();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeMedia(){
        start();
    }

}
