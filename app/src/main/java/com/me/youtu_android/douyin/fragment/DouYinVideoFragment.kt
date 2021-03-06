package com.me.youtu_android.douyin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.me.youtu_android.R
import com.me.youtu_android.douyin.bean.ClearPositionEvent
import com.me.youtu_android.douyin.bean.VideoList
import kotlinx.android.synthetic.main.activity_douyin.*
import kotlinx.android.synthetic.main.douyin_fragment_video.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DouYinVideoFragment : Fragment() {
    companion object {
        fun getNewInstance(videoBean: VideoList): DouYinVideoFragment {
            val fragment = DouYinVideoFragment()
            var bundle = Bundle()
            bundle.putParcelable("videoBean", videoBean)
            fragment.arguments = bundle
            return fragment
        }
    }

    var url = ""
    lateinit var mVideoBean: VideoList
    private var mCurrentPosition = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString("url") ?: ""
        mVideoBean = arguments?.getParcelable<VideoList>("videoBean")!!

        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.douyin_fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoPlayer?.apply {
            backButton.visibility = View.GONE
            titleTextView.visibility = View.GONE
            fullscreenButton.visibility = View.GONE

            isNeedShowWifiTip = true
            isLooping = true
            dismissControlTime = 1500
        }
        videoPlayer.setUpLazy(mVideoBean?.videoInfo?.videoUrl, true, null, null, "")

        likeLayout.onPauseListener = {
            if (videoPlayer.gsyVideoManager.isPlaying) {
                videoPlayer?.onVideoPause()
            } else {
                videoPlayer?.onVideoResume(false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (mCurrentPosition > 0) {
            videoPlayer?.onVideoResume(false)
        } else {
            videoPlayer?.startPlayLogic()
        }
    }

    override fun onPause() {
        super.onPause()
        likeLayout?.onPause()
        videoPlayer?.onVideoPause()
        mCurrentPosition = videoPlayer.gsyVideoManager?.currentPosition ?: 0
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventClearPosition(event: ClearPositionEvent) {
        if (event.isClear) {
            mCurrentPosition = 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        EventBus.getDefault().unregister(this)
    }

    val TAG: String = this.javaClass.simpleName

}