package com.me.youtu_android.douyin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.youtu_android.R
import com.me.youtu_android.douyin.daapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_douyin.*

class DouyinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douyin)
        viewPager.adapter = MainViewPagerAdapter(this)
        //停止用户输入的功能
        viewPager.isUserInputEnabled = false
    }
}
