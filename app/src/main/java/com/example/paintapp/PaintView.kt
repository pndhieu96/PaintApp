package com.example.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT


class PaintView: View {
    val TAG = PaintView::class.java.simpleName
    var params: ViewGroup.LayoutParams? = null
    private val TOUCH_TOLERANCE = 4f

    private var path = Path()
    private var paintBrush = Paint()
    private var pathList = ArrayList<Path>()
    private var colorList = ArrayList<Int>()
    private var mX = 0f
    private var mY = 0f
    var currentColor = Color.BLACK
        set(value) {
            field = value
            path = Path()
        }

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paintBrush.isAntiAlias = true
        paintBrush.color = currentColor
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f
        paintBrush.strokeCap = Paint.Cap.ROUND

        params = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                colorList.add(currentColor)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "MotionEvent ACTION_MOVE")
                touchMove(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
                return true
            }
            else -> return false
        }
        return false
    }

    override fun onDraw(canvas: Canvas) {
        Log.e(TAG, "onDraw")
        for(i in pathList.indices) {
            paintBrush.color = colorList[i]
            canvas.drawPath(pathList[i], paintBrush)
            Log.e(TAG, "onDraw color: $i - ${colorList[i]}")
        }
    }

    private fun touchStart(x: Float, y: Float) {
        path = Path()
        pathList.add(path)
        path.reset()
        path.moveTo(x, y)

        // we save the current
        // coordinates of the finger
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx: Float = Math.abs(x - mX)
        val dy: Float = Math.abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
    }

    private fun touchUp() {
        path.lineTo(mX, mY)
    }

    fun reset(){
        pathList.clear()
        colorList.clear()
        path.reset()
        invalidate()
    }
}