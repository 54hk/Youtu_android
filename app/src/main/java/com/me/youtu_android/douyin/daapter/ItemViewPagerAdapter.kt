package com.me.youtu_android.douyin.daapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.me.youtu_android.douyin.bean.VideoList
import com.me.youtu_android.douyin.fragment.DouYinInfoFragment
import com.me.youtu_android.douyin.fragment.DouYinVideoFragment

/**
 * @author Yun.Lei
 * @email waitshan@163.com
 * @date 2020/1/18
 */
class ItemViewPagerAdapter(fragment: Fragment, private val videoBean: VideoList) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> DouYinVideoFragment.getNewInstance(videoBean)
        else -> DouYinInfoFragment.getNewInstance(videoBean)
    }
}