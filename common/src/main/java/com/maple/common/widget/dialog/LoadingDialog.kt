package com.maple.common.widget.dialog

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.blankj.utilcode.util.ScreenUtils
import com.maple.baselib.widget.dialog.BaseCenterDialog
import com.maple.common.R

class LoadingDialog(context: Context):BaseCenterDialog(
    context,
    width = (ScreenUtils.getScreenWidth() * 0.8).toInt(),
    height = WindowManager.LayoutParams.WRAP_CONTENT,
    style = R.style.CommonDialogStyle){
    override fun getLayoutId(): Int = R.layout.dialog_loading

    override fun initData(savedInstanceState: Bundle?) {

    }

}