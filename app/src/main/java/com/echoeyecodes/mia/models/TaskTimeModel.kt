package com.echoeyecodes.jinx.models

data class TaskTimeModel(val hour:Int, val minute:Int){

    fun formatTimeDigits(value:Int) : String{
        val digit = "0$value"
        return digit.substring(digit.length - 2)
    }

    fun getFormattedHour():String{
        return formatTimeDigits(hour)
    }

    fun getFormattedMinute():String{
        return formatTimeDigits(minute)
    }

    fun getFormattedTime() : String{
        return formatTimeDigits(hour).plus(":").plus(formatTimeDigits(minute))
    }

}