package com.echoeyecodes.mia.fragments.dialogfragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.mia.R

class DatePickerDialogFragment() : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var date:TaskDateModel
    private lateinit var createTaskFragmentInterface: CreateTaskFragmentInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createTaskFragmentInterface = context as CreateTaskFragmentInterface
    }

    companion object {
        fun newInstance(date:TaskDateModel) = DatePickerDialogFragment().apply {
            this.date = date
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            date = savedInstanceState.getSerializable("DATE") as TaskDateModel
        }
        setStyle(STYLE_NORMAL, R.style.CustomAlertDialog)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("DATE", date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return DatePickerDialog(requireActivity(), this, date.year, date.month, date.date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        createTaskFragmentInterface.onDateSelected(TaskDateModel(dayOfMonth, month, year))
    }

}