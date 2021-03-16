package com.echoeyecodes.jinx.models

import java.io.Serializable

data class TaskDateModel(val date:Int, val month:Int, val year:Int) : Serializable{

    fun getMonth() : String{
        return when(month){
            0 -> "January"
            1 -> "February"
            2 -> "March"
            3 -> "April"
            4 -> "May"
            5 -> "June"
            6 -> "July"
            7 -> "August"
            8 -> "September"
            9 -> "October"
            10 -> "November"
            else -> "December"
        }
    }

}