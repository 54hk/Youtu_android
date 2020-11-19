package com.me.youtu_android.douyin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL
import com.me.youtu_android.R
import com.me.youtu_android.douyin.bean.ChangePageEvent
import com.me.youtu_android.douyin.bean.ToolbarOrEnabledStateEvent
import com.me.youtu_android.douyin.bean.VideoInfo
import com.me.youtu_android.douyin.bean.VideoList
import com.me.youtu_android.douyin.daapter.ItemViewPagerAdapter
import com.me.youtu_android.douyin.fragment.DouyinHomeFragment.Companion.douyin
import kotlinx.android.synthetic.main.douyin_fragment_item.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DouYinItemFragment : Fragment() {
    val TAG = this.javaClass.simpleName

    companion object {
        @JvmStatic
        fun getNewInstance(videoBean: VideoList): DouYinItemFragment {
            var douyinItemFragment = DouYinItemFragment()
            val bundle = Bundle()
            bundle.putParcelable("videoBean", videoBean)
            douyinItemFragment.arguments = bundle
            return douyinItemFragment;
        }
    }

    private var url = "";
    private lateinit var mVideoList:VideoList;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        url = arguments?.getString("url") ?: ""
        mVideoList = arguments?.getParcelable<VideoList>("videoBean")!!
        Log.e(TAG, "${mVideoList?.videoInfo}----简介-")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.douyin_fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewPager.adapter = ItemViewPagerAdapter(this, mVideoList)
        itemViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                //滑动infoFragment禁止滑动
//                if (positionOffset >= 0.5)
                if (position == 0 && positionOffset <= 0.5) {
                    EventBus.getDefault().post(ToolbarOrEnabledStateEvent(false))
                } else {
                    EventBus.getDefault().post(ToolbarOrEnabledStateEvent(true))
                }
                Log.e(douyin, "$position   $positionOffset   $positionOffsetPixels")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventChangePage(event: ChangePageEvent) {
        itemViewPager.currentItem = event.position
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}