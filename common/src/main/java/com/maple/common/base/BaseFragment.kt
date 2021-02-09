package com.maple.common.base

import android.app.Activity
import android.content.Intent
import com.zackratos.ultimatebarx.library.UltimateBarX
import kotlinx.android.synthetic.main.include_title.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import com.maple.baselib.utils.UIUtils
import com.maple.common.ext.toGone
import com.maple.common.ext.toVisible
import com.maple.common.utils.ToastUtils
import com.maple.common.widget.dialog.LoadingDialog
import com.maple.baselib.base.BaseFragment as B


abstract class BaseFragment : B() {

    private var loadingDialog: LoadingDialog? = null


    open fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = context?.let {
                LoadingDialog(it)
            }
        }
        loadingDialog?.show()
    }

    open fun dismissLoading() {
        loadingDialog?.run {
            if (isShowing) {
                runBlocking {
                    delay(500L)
                }
                dismiss()
            }
        }
    }

    open fun showToast(s: String?) {
        ToastUtils.showToast(s)
    }


    //-------------titleBar--------------------

    override fun setStatusBarMode(color: Int) {
        super.setStatusBarMode(color)
        UltimateBarX.with(this)
            .color(color)
            .applyStatusBar()
    }

    inline fun <reified T : BaseFragment> setTitle(txt: String): T {
        tv_title_center?.text = txt
        return this as T
    }

    inline fun <reified T : BaseFragment> setLeftTxt(txt: String): T {
        ll_title_left?.toVisible()
        tv_title_left?.apply {
            this.toVisible()
            this.text = txt
        }
        return this as T
    }

    inline fun <reified T : BaseFragment> setRightTxt(txt: String): T {
        ll_title_right?.toVisible()
        tv_title_right?.apply {
            this.toVisible()
            this.text = txt
        }
        return this as T
    }

    inline fun <reified T : BaseFragment> onBack(crossinline m: () -> Unit): T {
        ll_title_left?.apply {
            this.toVisible()
            this.setOnClickListener {
                if (!UIUtils.isFastDoubleClick()) {
                    m()
                }
            }
        }
        return this as T
    }

    inline fun <reified T : BaseFragment> onSide(crossinline m: () -> Unit): T {
        ll_title_right?.apply {
            this.toVisible()
            this.setOnClickListener {
                if (!UIUtils.isFastDoubleClick()) {
                    m()
                }
            }
        }
        return this as T
    }

    inline fun <reified T : BaseFragment> onUseBack(hasUse:Boolean): T {
        ll_title_left?.let {
            if(hasUse){
                it.toVisible()
            }else{
                it.toGone()
            }
        }
        return this as T
    }

    inline fun <reified T : BaseFragment> onUseSide(hasUse:Boolean): T {
        ll_title_left?.let {
            if(hasUse){
                it.toVisible()
            }else{
                it.toGone()
            }
        }
        return this as T
    }

    //------------end------------------


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            onEventResult(requestCode, data)
        }
    }

    /***
     * 页面返回结果回调封装
     *  子类重写
     */
    open fun onEventResult(requestCode: Int, data: Intent?) {}

}