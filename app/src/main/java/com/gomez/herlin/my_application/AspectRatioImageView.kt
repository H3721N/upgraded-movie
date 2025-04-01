package com.gomez.herlin.my_application

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class AspectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    var ratio: Float = 1f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        val height = measuredHeight

        if (width == 0 && height == 0) {
            return
        }

        if (width > 0) {
            val newHeight = (width * ratio).toInt()
            setMeasuredDimension(width, newHeight)
        } else if (height > 0) {
            val newWidth = (height / ratio).toInt()
            setMeasuredDimension(newWidth, height)
        }
    }

}