package com.me.youtu_android.douyin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.me.youtu_android.R
import com.me.youtu_android.douyin.daapter.MainViewPagerAdapter
import com.shuyu.gsyvideoplayer.utils.Debuger
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import kotlinx.android.synthetic.main.activity_douyin.*

class DouyinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).init()
        setContentView(R.layout.activity_douyin)
        Debuger.enable()
//16:9
        //切换渲染模式
        GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);

        viewPager.adapter = MainViewPagerAdapter(this)
        //停止用户输入的功能
        viewPager.isUserInputEnabled = false
    }
}
