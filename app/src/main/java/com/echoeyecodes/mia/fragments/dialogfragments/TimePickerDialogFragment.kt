package com.echoeyecodes.mia.fragments.dialogfragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskTimeModel
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.AndroidUtilities

class TimePickerDialogFragment() : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var time:TaskTimeModel
    private lateinit var createTaskFragmentInterface: CreateTaskFragmentInterface

    companion object{
        fun newInstance(time:TaskTimeModel) = TimePickerDialogFragment().apply {
            this.time = time
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createTaskFragmentInterface = context as CreateTaskFragmentInterface
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("TIME", time)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(requireActivity(), this, time.hour, time.minute, DateFormat.is24HourFormat(requireActivity()))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        createTaskFragmentInterface.onTimeSelected(TaskTimeModel(hourOfDay, minute))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            time = savedInstanceState.getSerializable("TIME") as TaskTimeModel
        }
        setStyle(STYLE_NORMAL, R.style.CustomAlertDialog)
    }


}