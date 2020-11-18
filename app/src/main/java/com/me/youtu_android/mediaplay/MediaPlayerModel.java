package com.me.youtu_android.mediaplay;

import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;

enum PlayStatus {
    playing, Paused, Completed, NotReady
}

public class MediaPlayerModel extends ViewModel {
    MyMediaPlayer mMediaPlayer = new MyMediaPlayer();
    //  是否显示加载
    public MutableLiveData<Integer> progressBarVisibily;
    //分辨率
    private MutableLiveData<Pair<Integer, Integer>> videoResoltion;
    //是否显示进度条
    private MutableLiveData<Integer> seekBarVisibily;
    private Long clickScreenTime = 0L;
    //seekbar展现 的进度
    private MutableLiveData<Integer> seeekBarProgress;
    //状态
    private MutableLiveData<PlayStatus> mPlayStatus;

    private String videoPath = "https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217021133Eggh6zdlAO.mp4";
    private static Handler handler = new Handler();

    public MutableLiveData<Integer> getProgressBarVisibily() {
        if (progressBarVisibily == null) {
            progressBarVisibily = new MutableLiveData<>();
            progressBarVisibily.setValue(View.VISIBLE);
        }
        return progressBarVisibily;
    }

    //  根据视频的比例去设置surfaceView显示宽高
    public MutableLiveData<Pair<Integer, Integer>> getVideoResoltion() {
        if (videoResoltion == null) {
            videoResoltion = new MutableLiveData<>();
            videoResoltion.setValue(new Pair<Integer, Integer>(0, 0));
        }
        return videoResoltion;
    }

    public MutableLiveData<Integer> getseekBarVisibily() {
        if (seekBarVisibily == null) {
            seekBarVisibily = new MutableLiveData<>();
            seekBarVisibily.setValue(View.GONE);
        }
        return seekBarVisibily;
    }

    public MutableLiveData<Integer> getSeeekBarProgress() {
        if (seeekBarProgress == null) {
            seeekBarProgress = new MutableLiveData<>();
            seeekBarProgress.setValue(0);
        }
        return seeekBarProgress;
    }

    public MutableLiveData<PlayStatus> getmPlayStatus() {
        if (mPlayStatus == null) {
            mPlayStatus = new MutableLiveData<>();
            mPlayStatus.setValue(PlayStatus.NotReady);
        }
        return mPlayStatus;
    }

    public void toggleSeekBar() {
        if (getseekBarVisibily().getValue() == View.GONE) {
            getseekBarVisibily().setValue(View.VISIBLE);
            clickScreenTime = System.currentTimeMillis();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (System.currentTimeMillis() - clickScreenTime > 2500) {
                        getseekBarVisibily().setValue(View.GONE);
                    }
                }
            }, 3000);

        } else {
            getseekBarVisibily().setValue(View.GONE);
        }
    }

    public void onLoad() {
        try {
            getmPlayStatus().setValue(PlayStatus.NotReady);
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(videoPath);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.e("UUU", "MediaPlayer -- onPrepared");
                    getProgressBarVisibily().setValue(View.INVISIBLE);
                    mp.start();
                    getmPlayStatus().setValue(PlayStatus.playing);
                }
            });
            mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    getVideoResoltion().setValue(new Pair<Integer, Integer>(width, height));
                }
            });
            mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.e("UUUU", "percent --- " + percent);
                    getSeeekBarProgress().setValue(percent);
                }
            });
            mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    mp.start();
                    getseekBarVisibily().setValue(View.GONE);
                    getmPlayStatus().setValue(PlayStatus.playing);
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    getmPlayStatus().setValue(PlayStatus.Completed);
                }
            });
            mMediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void setmMediaPlayerProgress(int time) {
        getseekBarVisibily().setValue(View.VISIBLE);
        mMediaPlayer.seekTo(time);
        mMediaPlayer.start();
        getmPlayStatus().setValue(PlayStatus.playing);
    }

    public void togglePlayStatus() {
        switch (getmPlayStatus().getValue()) {
            case Completed:
            case Paused:
                mMediaPlayer.start();
                getmPlayStatus().setValue(PlayStatus.playing);
                break;
            case playing:
                mMediaPlayer.pause();
                getmPlayStatus().setValue(PlayStatus.Paused);
                break;
            case NotReady:
                break;

        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mMediaPlayer.release();
    }
}
