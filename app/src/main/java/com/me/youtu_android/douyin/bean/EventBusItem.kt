package com.me.youtu_android.douyin.bean

//滑动到infofragment页面， 是否显示toobar \ 禁止垂直滑动
data class ToolbarOrEnabledStateEvent(var isShow: Boolean)
//改变页面
data class ChangePageEvent(var position: Int)

data class ClearPositionEvent(var isClear: Boolean)

