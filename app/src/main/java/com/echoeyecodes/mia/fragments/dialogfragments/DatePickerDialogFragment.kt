package com.echoeyecodes.jinx.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.interfaces.SelectTimeFragmentInterface
import com.echoeyecodes.jinx.models.TaskDateModel

class DatePickerDialogFragment(private val date: TaskDateModel, private val createTaskFragmentInterface: CreateTaskFragmentInterface) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return DatePickerDialog(requireActivity(), this, date.year, date.month, date.date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        createTaskFragmentInterface.onDateSelected(TaskDateModel(dayOfMonth, month, year))
    }

}