package com.echoeyecodes.jinx.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskTimeModel

class TimePickerDialogFragment(private val time:TaskTimeModel, private val createTaskFragmentInterface: CreateTaskFragmentInterface) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return TimePickerDialog(requireActivity(), this, time.hour, time.minute, DateFormat.is24HourFormat(requireActivity()))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        createTaskFragmentInterface.onTimeSelected(TaskTimeModel(hourOfDay, minute))
    }


}