package com.me.youtu_android.douyin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.me.youtu_android.R
import com.me.youtu_android.douyin.bean.ChangePageEvent
import com.me.youtu_android.douyin.bean.DataBean
import com.me.youtu_android.douyin.bean.VideoList
import com.me.youtu_android.douyin.fragment.DouyinHomeFragment.Companion.douyin
import kotlinx.android.synthetic.main.douyin_fragment_info.*
import kotlinx.android.synthetic.main.douyin_fragment_video.*
import org.greenrobot.eventbus.EventBus

class DouYinInfoFragment : Fragment() {
    companion object {
        fun getNewInstance(videoBean: VideoList): DouYinInfoFragment {
            val fragment = DouYinInfoFragment()
            var bundle = Bundle()
            bundle.putParcelable("videoBean", videoBean)
            fragment.arguments = bundle
            return fragment
        }
    }
    val TAG = this.javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e(TAG ,"DouyinInfoFragment --- ")
        return inflater.inflate(R.layout.douyin_fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backBtn.setOnClickListener {

            EventBus.getDefault().post(ChangePageEvent(0))
        }
    }

}