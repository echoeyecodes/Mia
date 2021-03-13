package com.echoeyecodes.jinx.interfaces

import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel

interface CreateTaskFragmentInterface {
    fun openDateTimeDialog()
    fun onDateSelected(date:TaskDateModel)
    fun onTimeSelected(time: TaskTimeModel)
    fun setDateType(idx:Int)
    fun onDefaultDateTimeSelected(date: TaskDateModel, time: TaskTimeModel)
    fun setIgnoreDateTime(value: Boolean)
}