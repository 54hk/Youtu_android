package com.me.youtu_android.mediaplay;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.me.youtu_android.R;
import com.me.youtu_android.databinding.ActivityMediaPlayerBinding;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MediaPlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {

    private MediaPlayerModel mMediaPlayerModel;
    private ActivityMediaPlayerBinding mMediaPlayerBinding;
    private boolean isChangeSeekBar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        Log.e("UUU", "oncreat -----");
        mMediaPlayerBinding = DataBindingUtil.setContentView(this, R.layout.activity_media_player);
        mMediaPlayerModel = ViewModelProviders.of(this).get(MediaPlayerModel.class);
//        //观察生命周期
        getLifecycle().addObserver(mMediaPlayerModel.mMediaPlayer);
//
        updateSeekBarProgress();
        mMediaPlayerModel.getProgressBarVisibily().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.e("UUU", "integer" + integer);
                mMediaPlayerBinding.progressBar.setVisibility(integer);
            }
        });
        mMediaPlayerModel.getVideoResoltion().observe(this, new Observer<Pair<Integer, Integer>>() {
            @Override
            public void onChanged(final Pair<Integer, Integer> integerIntegerPair) {

                mMediaPlayerBinding.containerLayout.seekBar2.setMax(mMediaPlayerModel.mMediaPlayer.getDuration());
                mMediaPlayerBinding.progressBar.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("UUU", "" + integerIntegerPair.first + "---" + integerIntegerPair.second);
                        resizePlayer(integerIntegerPair.first, integerIntegerPair.second);
                    }
                });
            }
        });
        mMediaPlayerModel.getseekBarVisibily().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mMediaPlayerBinding.containerLayout.containerFramelayout.setVisibility(integer);

            }
        });
        mMediaPlayerModel.getSeeekBarProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //设置缓存区的进度
                mMediaPlayerBinding.containerLayout.seekBar2.setSecondaryProgress(mMediaPlayerBinding.containerLayout.seekBar2.getMax() * integer / 100);

            }
        });
        mMediaPlayerModel.getmPlayStatus().observe(this, new Observer<PlayStatus>() {

            @Override
            public void onChanged(PlayStatus playStatus) {
                mMediaPlayerBinding.containerLayout.seekBar2.setClickable(true);
                switch (playStatus) {
                    case Paused:
                        Log.d("TAG", "onChanged:1 ");
                        mMediaPlayerBinding.containerLayout.butPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
                        break;
                    case playing:
                        Log.d("TAG", "onChanged:2 ");
                        mMediaPlayerBinding.containerLayout.butPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                        break;
                    case NotReady:
                        Log.d("TAG", "onChanged:3 ");
                        mMediaPlayerBinding.containerLayout.seekBar2.setClickable(false);
                        break;
                    case Completed:

                        Log.d("TAG", "onChanged:4 ");
                        mMediaPlayerBinding.containerLayout.butPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_replay_black_24dp));
                        break;
                }
            }
        });
        mMediaPlayerBinding.surfaceView.getHolder().addCallback(this);
        mMediaPlayerModel.onLoad();

        mMediaPlayerBinding.containerLayout.seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mMediaPlayerModel.setmMediaPlayerProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChangeSeekBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isChangeSeekBar = false;
//                updateSeekBarProgress();
//                mMediaPlayerModel.setmMediaPlayerProgress(seekBar.getProgress());
            }
        });
        mMediaPlayerBinding.containerLayout.butPlay.setOnClickListener(this);
//  点击屏幕处理
        mMediaPlayerBinding.frameLayout.setOnClickListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaPlayerModel.mMediaPlayer.setDisplay(holder);
        //播放的时候永远屏幕亮
        mMediaPlayerModel.mMediaPlayer.setScreenOnWhilePlaying(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void resizePlayer(int width, int height) {
        if (width == 0 || height == 0)
            return;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                mMediaPlayerBinding.surfaceView.getHeight() * width / height,
                FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
        );
        mMediaPlayerBinding.surfaceView.setLayoutParams(layoutParams);

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.setRequestedOrientation(getResources().getConfiguration().orientation);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Log.e("TAG", "ORIENTATION_LANDSCAPE");
        }

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            Log.e("TAG", "ORIENTATION_PORTRAIT");
        }
    }

    //    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
//            hideSystemUI();
//            Log.e("UUU", "-=-=-=---=-=-=-=-=");
//        } else {
//            showSystemUI();
//        }
//
//
//        Log.e("UUU", getResources().getConfiguration().orientation + "===     " + MediaPlayerModel.mMediaPlayer.getVideoWidth() + "-=-=-=onWindowFocusChanged---=-=-=-=-=" + MediaPlayerModel.mMediaPlayer.getVideoHeight());
//    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    static Handler handler = new Handler();

    public void updateSeekBarProgress() {
//        while (true) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (isChangeSeekBar) {
//                    return;
//                }
                mMediaPlayerBinding.containerLayout.seekBar2.setProgress(mMediaPlayerModel.mMediaPlayer.getCurrentPosition());

                updateSeekBarProgress();
            }
        }, 500);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //暂停、播放
            case R.id.but_play:
                mMediaPlayerModel.togglePlayStatus();
                break;
            //点击屏幕
            case R.id.frameLayout:
                mMediaPlayerModel.toggleSeekBar();
                break;
        }
    }
}