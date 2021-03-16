package com.echoeyecodes.jinx.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel
import com.echoeyecodes.mia.models.DaysModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class CreateTaskViewModel : ViewModel() {
    val selectedDateLiveData = MutableLiveData<TaskDateModel>()
    val selectedTimeLiveData = MutableLiveData<TaskTimeModel>()
    val shouldRepeat = MutableLiveData<Boolean>()
    val ignoreDateTime = MutableLiveData(true)
    val days = MutableLiveData<List<DaysModel>>()
    var dateType = MutableLiveData(0)
    val monthDate = MutableLiveData(1)

    init {
        val date = LocalDate.now()
        val time = LocalTime.now()

        val now = TaskDateModel(date.dayOfMonth, date.monthValue - 1, date.year)
        val timeNow = TaskTimeModel(time.hour, time.minute)

        selectedDateLiveData.value = now
        selectedTimeLiveData.value = timeNow
        shouldRepeat.value = false
        days.value = listOf(DaysModel("S", false), DaysModel("M", true), DaysModel("T", false), DaysModel("W", false), DaysModel("T", false), DaysModel("F", false), DaysModel("S", false), )
    }

    fun selectedDateToString() : String{
        val today = LocalDateTime.now()
        val date = selectedDateLiveData.value!!

        val time = selectedTimeLiveData.value!!

        return if(today.dayOfMonth == date.date && (today.monthValue - 1) == date.month && today.year == date.year){
            "Today, ${time.getFormattedTime()}"
        }else if(date.date == today.plusDays(1).dayOfMonth && date.year == today.year){
            "Tomorrow, ${time.getFormattedTime()}"
        }else{
            "${date.getMonth()} ${date.date}, ${time.getFormattedTime()}"
        }
    }

    fun setSelectedDays(idx:Int){
        val previousList = ArrayList(days.value!!)
        val focus = previousList[idx]
        previousList[idx] = focus.copy(selected = !focus.selected)
        days.postValue(previousList)
        
    }
}