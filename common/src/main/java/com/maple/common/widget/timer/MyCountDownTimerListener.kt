package com.maple.common.widget.timer

interface MyCountDownTimerListener {
    fun onStart()
    fun onTick(millisUntilFinished: Long)
    fun onFinish()
}