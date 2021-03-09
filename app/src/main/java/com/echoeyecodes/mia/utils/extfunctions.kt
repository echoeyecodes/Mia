package com.echoeyecodes.mia.utils

import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue


fun Int.convertToDp() : Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
        .toInt()
}

fun String.convertHexToColor(): Int {
    return Color.parseColor(this)
}