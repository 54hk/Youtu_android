package com.me.youtu_android.douyin.daapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.me.youtu_android.douyin.fragment.DouyinHomeFragment

/**
 * @author Yun.Lei
 * @email waitshan@163.com
 * @date 2020/1/18
 */
class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment = DouyinHomeFragment.getNewInstance()
}