package com.me.youtu_android.douyin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.*
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import com.me.youtu_android.Constants
import com.me.youtu_android.R
import com.me.youtu_android.douyin.bean.ClearPositionEvent
import com.me.youtu_android.douyin.bean.DataBean
import com.me.youtu_android.douyin.bean.ToolbarOrEnabledStateEvent
import com.me.youtu_android.douyin.bean.VideoList
import com.me.youtu_android.douyin.daapter.HomeViewPagerAdapter
import com.me.youtu_android.douyin.widget.FixedSpeedScroller
import com.me.youtu_android.okhttp.MyOkhttpUtils
import com.me.youtu_android.okhttp.OkCallBack
import kotlinx.android.synthetic.main.activity_douyin.viewPager
import kotlinx.android.synthetic.main.douyin_fragment_home.*
import kotlinx.android.synthetic.main.douyin_layout_home_title.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

//https://test.artfirecn.com/YihuoService/services/head/getrelatedshortvideolist?requestUmiid=22682&tudId=8213&start=0&length=5&ype=3&umiid=185117
class DouyinHomeFragment : Fragment() {
    val TAG: String = this.javaClass.simpleName

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
    private val mVideoList = mutableListOf<VideoList>()
    private lateinit var mAdapter: HomeViewPagerAdapter
    private var isLoading: Boolean = false
    private var mPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadDate(mPage, 0)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.douyin_fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ImmersionBar.with(this).titleBar(toolBar).init()

        mAdapter = HomeViewPagerAdapter(this, mVideoList)
        viewPager.adapter = mAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            //TODO 三个方法
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                EventBus.getDefault().post(ClearPositionEvent(true))

                if (position == mVideoList.size - 1) {

                }
//                Log.e(douyin, "${DouyinHomeFragment.javaClass.simpleName}  --  onPageSelected $position")
            }

            var mState: Int = -1
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    SCROLL_STATE_IDLE -> {
                        mState = SCROLL_STATE_IDLE
                        Log.e(TAG, "SCROLL_STATE_IDLE")
                    }
                    SCROLL_STATE_DRAGGING -> {
                        mState = SCROLL_STATE_DRAGGING
                        Log.e(TAG, "SCROLL_STATE_DRAGGING ======")
                    }
                    SCROLL_STATE_SETTLING -> {
                        mState = SCROLL_STATE_SETTLING
                        Log.e(TAG, "SCROLL_STATE_SETTLING-------")
                    }
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                //上拉，加载新的数据
                if (mState == SCROLL_STATE_DRAGGING && position == mVideoList.size - 1) {
                    isLoading = true
                    dy_loadingview.start()
                    dy_loadingview.visibility = View.VISIBLE
                    loadDate(++ mPage, mVideoList.size)
                    mState = -1
                }
                Log.e(TAG, "mpage    $mPage     position  $position mVideoList.size ${mVideoList.size}   positionOffset   $positionOffset   positionOffsetPixels   $positionOffsetPixels")
            }
        })
//        setScroller()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventToolbarOrEnabledState(event: ToolbarOrEnabledStateEvent) {
        //禁止滑动
        viewPager.isUserInputEnabled = !event.isShow
        //是否显示toobar
        toolBar.visibility = if (!event.isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        EventBus.getDefault().unregister(this)
    }

    fun loadDate(start: Int, size: Int) {
        var map = mutableMapOf("requestUmiid" to "22682", "tudId" to "8213", "start" to start.toString(), "length" to "5", "umiid" to "185117")
        MyOkhttpUtils.getInstance().postNet(activity as AppCompatActivity?, Constants.BASE_URL, map, false, object : OkCallBack {
            override fun onFailure(e: Exception?) {
                Log.e(TAG, " onFailure  ")
                dy_loadingview.stop()
                dy_loadingview.visibility = View.GONE
            }

            override fun onResponse(data: String?) {
                Log.e(TAG, "数据加载--- ")

                var gson = Gson()
                val fromJson = gson.fromJson(data, DataBean::class.java)
                val list = fromJson.appendData?.list
                if (list != null) {

                    mVideoList.addAll(list)
                    mAdapter.notifyDataSetChanged()

                    viewPager.currentItem = size
                }
                dy_loadingview.stop()
                dy_loadingview.visibility = View.GONE

            }

        })
    }

    override fun onPause() {
        super.onPause()
        dy_loadingview.stop()
    }

    //  控制viewpager的滑动速度
    fun setScroller() {

        try {
            val field = ViewPager2::class.java.getDeclaredField("mScroller");
            field.isAccessible = true;
            val scroller = FixedSpeedScroller(viewPager.context);
            field.set(viewPager, scroller);
            scroller.setmDuration(2000);
        } catch (e: Exception) {
            Log.e(TAG, "", e);
        }
    }
}