package com.me.youtu_android.douyin.daapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.me.youtu_android.douyin.bean.VideoList
import com.me.youtu_android.douyin.fragment.DouYinItemFragment

/**
 * @author Yun.Lei
 * @email waitshan@163.com
 * @date 2020/1/18
 */
class HomeViewPagerAdapter(fragment: Fragment, private val list:MutableList<VideoList>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = DouYinItemFragment.getNewInstance(list[position])
}