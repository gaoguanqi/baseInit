package com.maple.common.utils

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.interpolator.view.animation.FastOutLinearInInterpolator

class AnimUtils {
    companion object {

        //展开
        fun expand(view: View){
            view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val viewHeight = view.measuredHeight
            view.layoutParams.height = 0
            view.visibility = View.VISIBLE
            val animation: Animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    super.applyTransformation(interpolatedTime, t)
                    view.layoutParams.height = if (interpolatedTime == 1.0f) {
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    } else {
                        (viewHeight * interpolatedTime).toInt()
                    }
                    view.requestLayout()
                }
            }
            animation.duration = 300L
            animation.interpolator = FastOutLinearInInterpolator()
            view.startAnimation(animation)
        }

        //关闭
        fun collapse(view: View){
            view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val viewHeight = view.measuredHeight
            val animation: Animation = object : Animation(){
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    super.applyTransformation(interpolatedTime, t)
                    if (interpolatedTime == 1.0f){
                        view.visibility = View.GONE
                    }else{
                        view.layoutParams.height = viewHeight - (viewHeight * interpolatedTime).toInt()
                        view.requestLayout()
                    }
                }
            }
            animation.duration = 300L
            animation.interpolator = FastOutLinearInInterpolator()
            view.startAnimation(animation)
        }
    }
}