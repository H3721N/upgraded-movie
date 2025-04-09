package com.gomez.herlin.my_application.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.gomez.herlin.my_application.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AspectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    var ratio: Float = 1f

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
        ratio = a.getFloat(R.styleable.AspectRatioImageView_ratio, 1f)
        a.recycle()
    }

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