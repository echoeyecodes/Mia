package com.echoeyecodes.jinx.utils

import java.time.LocalDate

class CustomDateFormatter {

    fun getWeekDay(dayOfWeek: Int) : String{
        return when(dayOfWeek){
            0 -> "SUN"
            1 -> "MON"
            2 -> "TUE"
            3 -> "WED"
            4 -> "THU"
            5 -> "FRI"
            else -> "SAT"
        }
    }
}