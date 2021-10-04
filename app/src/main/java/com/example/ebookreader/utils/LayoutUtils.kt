package com.example.ebookreader.utils

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class CustomTypefaceSpan(private val typeface: Typeface?) : MetricAffectingSpan() {
    override fun updateDrawState(paint: TextPaint) {
        paint.typeface = typeface
    }

    override fun updateMeasureState(paint: TextPaint) {
        paint.typeface = typeface
    }
}

class TypeFaceStyler(
    private val context: Context,
    textView: TextView,
) {
    private val contextText: String = textView.text.toString()
    var spannable: SpannableStringBuilder = SpannableStringBuilder(contextText)

    fun styleText(targetText: String, fontResource: Int?): TypeFaceStyler {
        val targetIndex = contextText.indexOf(targetText)

        fontResource?.let {
            spannable.setSpan(CustomTypefaceSpan(ResourcesCompat.getFont(context, it)), targetIndex, targetIndex + targetText.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        }

        return this
    }
}