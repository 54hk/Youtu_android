package com.me.youtu_android.douyin.widget

import android.content.Context
import android.graphics.Interpolator
import android.widget.Scroller

class FixedSpeedScroller : Scroller {
    private var mDuration = 1500

    constructor(context: Context) : super(context)


    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    fun setmDuration(time: Int) {
        mDuration = time
    }

    fun getmDuration(): Int {
        return mDuration
    }

}