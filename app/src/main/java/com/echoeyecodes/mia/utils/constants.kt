package com.echoeyecodes.mia.utils

import android.content.res.Resources

class Dimensions(val width:Int, val height:Int)

fun getNavigationHeight() : Int {
    val resources = Resources.getSystem()
    val resource = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if(resource > 0){
        return resources.getDimensionPixelSize(resource)
    }
    return 0
}

fun bottomNavigationViewHeight() : Int{
    return 56.convertToDp()
}

fun getScreenSize():Dimensions{
    return Dimensions(Resources.getSystem().displayMetrics.widthPixels, Resources.getSystem().displayMetrics.heightPixels)
}