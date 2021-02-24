package com.maple.common.widget.view
import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.blankj.utilcode.util.KeyboardUtils
import com.maple.common.R

/**
 * 自定义 验证码控件 (仿网易云音乐)
 *
 * 如何使用?
 *
 *
    <com.maple.common.widget.view.VerifyCodeView
        android:id="@+id/verifyCodeView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>


    VerifyCodeHandler(this).setView(this,verifyCodeView)
        verifyCodeView.setListener(object :VerifyCodeView.InputCompleteListener{
            override fun inputComplete(content: String) {
            LogUtils.logGGQ("-->${content}")
        }

        override fun invalidContent() {
            LogUtils.logGGQ("-->invalidContent")
        }
    })

 *
 */
class VerifyCodeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {


    private val editText: CursorEditText

    fun getEditText() = editText

    private val textViews: Array<TextView>
    private var inputContent: String = ""

    private val MAX: Int = 4

    private var listener: InputCompleteListener? = null

    fun setListener(listener: InputCompleteListener) {
        this.listener = listener
    }


    init {
        View.inflate(context, R.layout.layout_verify_code, this)
        textViews = arrayOf<TextView>(
            findViewById(R.id.tv_0),
            findViewById(R.id.tv_1),
            findViewById(R.id.tv_2),
            findViewById(R.id.tv_3)
        )
        editText = findViewById(R.id.et_input)
        editText.isCursorVisible = false //隐藏光标
        setEditTextListener()
        KeyboardUtils.showSoftInput(editText)
    }

    private fun setEditTextListener() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                inputContent = editText.text.toString()
                inputContent.apply {
                    if (inputContent.length >= MAX) {
                        listener?.inputComplete(inputContent)
                        KeyboardUtils.hideSoftInput(editText)
                    } else {
                        listener?.invalidContent()
                    }
                }

                for(index in textViews.indices){
                    if (index < inputContent.length) {
                        textViews[index].text = inputContent[index].toString()
                    } else {
                        textViews[index].text = ""
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }


    fun setContent(s:String = ""){
        inputContent = s
        editText.setText(inputContent)
    }

    fun initFocus(activity: Activity){
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    fun clear(){
        inputContent = ""
        editText.setText(inputContent)
        KeyboardUtils.hideSoftInput(editText)
    }

    interface InputCompleteListener {
        fun inputComplete(content:String)
        fun invalidContent()
    }
}