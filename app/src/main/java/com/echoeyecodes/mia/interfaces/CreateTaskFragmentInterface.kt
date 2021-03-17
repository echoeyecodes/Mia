package com.echoeyecodes.mia.interfaces

import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel
import java.io.Serializable

interface CreateTaskFragmentInterface : Serializable{
    fun onDateSelected(date:TaskDateModel)
    fun onTimeSelected(time: TaskTimeModel)
    fun setDateType(idx:Int)
}