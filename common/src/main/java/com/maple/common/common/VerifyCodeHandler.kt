package com.maple.common.common

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.maple.baselib.app.manager.LifecycleHandler
import com.maple.baselib.utils.LogUtils
import com.maple.common.widget.view.VerifyCodeView

class VerifyCodeHandler(lifecycleOwner: LifecycleOwner):LifecycleHandler(lifecycleOwner) {

    private var verifyCodeView: VerifyCodeView? = null
    private var activity:Activity? = null
    fun setView(act: Activity, view:VerifyCodeView){
        this.activity = act
        this.verifyCodeView = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        activity?.let {
            verifyCodeView?.initFocus(it)
        }
        LogUtils.logGGQ("map view ->>onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        verifyCodeView?.clear()
        LogUtils.logGGQ("verifyCodeView ->>onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        super.onDestroy()
        LogUtils.logGGQ("verifyCodeView ->>onDestroy")
    }
}