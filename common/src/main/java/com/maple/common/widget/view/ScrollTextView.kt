package com.maple.common.widget.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.maple.common.R

class ScrollTextView : AppCompatTextView {


    private lateinit var defaultPaint: Paint
    private lateinit var changePaint: Paint

    //进度
    private var currentProgress: Float = 0.0f
    //方向
    private var direction: Direction = Direction.LEFT_TO_RIGHT

    enum class Direction {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    constructor(context: Context) : super(context,null,0)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet,0)

    @JvmOverloads constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr){
        initView(context, attributeSet, defStyleAttr)
    }

    private fun initView(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int){
        val ta:TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollTextView)
        //默认颜色
        val defaultColor:Int = ta.getColor(R.styleable.ScrollTextView_defaultColor,textColors.defaultColor)
        //改变颜色
        val changeColor:Int = ta.getColor(R.styleable.ScrollTextView_changeColor,textColors.defaultColor)
        //回收
        ta.recycle()
        defaultPaint = getPaintByColor(defaultColor)
        changePaint = getPaintByColor(changeColor)
    }

    private fun getPaintByColor(color:Int):Paint{
        val paint:Paint = Paint()
        //设置颜色
        paint.setColor(color)
        //抗锯齿
        paint.isAntiAlias = true
        //防抖动
        paint.isDither = true
        //字体大小
        paint.textSize = textSize
        return paint
    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        val middle: Int = (currentProgress * width).toInt()
        if(direction == Direction.LEFT_TO_RIGHT){
            drawText(canvas,changePaint,0,middle)
            drawText(canvas,defaultPaint,middle,width)
        }else{
            drawText(canvas,changePaint,width-middle,width)
            drawText(canvas,defaultPaint,0,width-middle)
        }
    }

    private fun drawText(canvas: Canvas?,paint: Paint,start: Int,end: Int){
        canvas?.let {
            it.save()
            val text:String = text.toString()
            val rect = Rect(start,0,end,height)
            it.clipRect(rect)
            paint.getTextBounds(text,0,text.length,rect)
            val x:Float = width / 2.0f - rect.width() / 2.0f
            val fontMetricsInt: Paint.FontMetricsInt = paint.getFontMetricsInt()
            val y:Float = (fontMetricsInt.bottom - fontMetricsInt.top) / 2.0f - fontMetricsInt.bottom
            val baseLine:Float = height / 2.0f + y
            it.drawText(text,x,baseLine,paint)
            it.restore()
        }
    }


    //设置方向
    fun setDirection(dic:Direction){
        this.direction = dic
    }

    //设置最大进度
    fun setCurrentProgress(process: Float){
        this.currentProgress = process
        this.invalidate()
    }

    //设置改变颜色
    fun setChangeColor(color: Int){
        this.changePaint.setColor(color)
    }
}