package com.me.youtu_android.douyin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.me.youtu_android.R
import com.me.youtu_android.douyin.bean.*
import com.me.youtu_android.douyin.daapter.HomeViewPagerAdapter
import kotlinx.android.synthetic.main.activity_douyin.*
import kotlinx.android.synthetic.main.douyin_fragment_item.*
import kotlinx.android.synthetic.main.douyin_layout_home_title.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DouyinHomeFragment : Fragment() {
    companion object {
        @JvmField
        val douyin: String = "DouYin"

        @JvmStatic
        fun getNewInstance(): DouyinHomeFragment = DouyinHomeFragment()
    }

    private val urlList = mutableListOf(
            "http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4",
            "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4",
            "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4",
            "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4",
            "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4"
    )
    private lateinit var mAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.douyin_fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = HomeViewPagerAdapter(this, urlList)
        viewPager.adapter = mAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            //TODO 三个方法
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                EventBus.getDefault().post(ClearPositionEvent(true))
                Log.e(douyin, "${DouyinHomeFragment.javaClass.simpleName}  --  onPageSelected $position")
            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventToolbarOrEnabledState(event: ToolbarOrEnabledStateEvent) {
        //禁止滑动
        viewPager.isUserInputEnabled = !event.isShow
        //是否显示toobar
        toolBar.visibility = if (!event.isShow){
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        EventBus.getDefault().unregister(this)
    }
}