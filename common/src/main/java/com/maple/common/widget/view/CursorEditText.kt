package com.maple.common.widget.view
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText

class CursorEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var lastTime:Long = 0L

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        //把光标位置固定在最末
        this.text?.length?.let { this.setSelection(it) }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                val currentTime:Long = System.currentTimeMillis()
                if(currentTime - lastTime < 500){
                    lastTime = currentTime
                    return true
                }else{
                    lastTime = currentTime
                }
            }
        }
        return super.onTouchEvent(event)
    }
}