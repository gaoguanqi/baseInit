package com.maple.test.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.blankj.utilcode.util.KeyboardUtils
import com.maple.baselib.utils.LogUtils
import com.maple.common.base.BaseActivity
import com.maple.common.common.VerifyCodeHandler
import com.maple.common.widget.view.ExpandLinearLayout
import com.maple.common.widget.view.VerifyCodeView
import com.maple.test.R

class HomeActivity:BaseActivity() {
    private lateinit var exll:ExpandLinearLayout
    lateinit var btnTest:Button
    lateinit var ivIcon:ImageView
    lateinit var verifyCodeView:VerifyCodeView

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun initData(savedInstanceState: Bundle?) {
        showToast("home")
        exll = findViewById(R.id.exll)
        verifyCodeView = findViewById(R.id.verifyCodeView)
        ivIcon = findViewById(R.id.iv_icon)
        btnTest = findViewById(R.id.btn_test)
        btnTest.setOnClickListener {
            val toggle = exll.toggle(icRotate = ivIcon)
            btnTest.text = if (toggle) "收起" else "展开"
        }

        VerifyCodeHandler(this).setView(this,verifyCodeView)
        verifyCodeView.setListener(object :VerifyCodeView.InputCompleteListener{
            override fun inputComplete(content: String) {
                LogUtils.logGGQ("-->${content}")
            }
            override fun invalidContent() {
                LogUtils.logGGQ("-->invalidContent")
            }
        })
    }
}