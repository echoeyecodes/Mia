package com.echoeyecodes.jinx.fragments.BottomSheetFragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.jinx.adapters.RepeatWeekAdapter
import com.echoeyecodes.jinx.fragments.DatePickerDialogFragment
import com.echoeyecodes.jinx.fragments.TimePickerDialogFragment
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel
import com.echoeyecodes.jinx.utils.DaysItemCallback
import com.echoeyecodes.jinx.viewmodel.CreateTaskViewModel
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.fragments.dialogfragments.ScheduleFragment
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CreateTodoDialogFragment : BottomSheetDialogFragment(), CreateTaskFragmentInterface {

    private lateinit var descriptionBtn:ImageButton
    private lateinit var dateBtn:TextView
    private lateinit var titleEditText: TextInputEditText
    private lateinit var descriptionTextContainer:TextInputLayout
    private lateinit var viewModel: CreateTaskViewModel
    private var selectTimeFragment: SelectTimeFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_task_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CreateTaskViewModel::class.java)

        descriptionTextContainer = view.findViewById(R.id.task_description_container)
        titleEditText = view.findViewById(R.id.task_title)
        descriptionBtn = view.findViewById(R.id.task_description_btn)
        dateBtn = view.findViewById(R.id.task_date_btn)

        descriptionBtn.setOnClickListener {
            descriptionTextContainer.visibility = View.VISIBLE
        }

        selectTimeFragment = SelectTimeFragment(this)
        dateBtn.setOnClickListener { selectTimeFragment?.show(childFragmentManager, "SELECT_TIME_FRAGMENT") }

        viewModel.selectedTimeLiveData.observe(this, {
            dateBtn.text = viewModel.selectedDateToString()
        })

        viewModel.selectedTimeLiveData.observe(this, {
            dateBtn.text = viewModel.selectedDateToString()
        })

        viewModel.selectedDateLiveData.observe(this, {
            dateBtn.text = viewModel.selectedDateToString()
        })

        viewModel.ignoreDateTime.observe(this, {
            if(it){
                dateBtn.text = "Unassigned"
            }else{
                dateBtn.text = viewModel.selectedDateToString()
            }
        })
    }

    override fun openDateTimeDialog() {
        val fragment = ScheduleFragment(this)
        fragment.show(childFragmentManager, "SCHEDULE_FRAGMENT")
        selectTimeFragment?.dismiss()
    }

    override fun onDateSelected(date: TaskDateModel) {
        viewModel.selectedDateLiveData.value = date
    }


    override fun onTimeSelected(time: TaskTimeModel) {
        viewModel.selectedTimeLiveData.value = time
    }

    override fun setDateType(idx: Int) {
        viewModel.setSelectedDays(idx)
    }

    override fun onDefaultDateTimeSelected(date: TaskDateModel, time: TaskTimeModel) {
        run {
            onDateSelected(date)
            onTimeSelected(time)
            selectTimeFragment?.dismiss()
        }
    }

    override fun setIgnoreDateTime(value: Boolean) {
        viewModel.ignoreDateTime.value = value
        selectTimeFragment?.dismiss()
    }

    private fun openDialogFragment(fragmentManager:FragmentManager, fragment:DialogFragment?, fragmentTag:String){
        fragment?.let {
            if(!it.isVisible){
                it.show(fragmentManager, fragmentTag)
            }
        }
    }

    companion object {
        fun newInstance(): CreateTodoDialogFragment = CreateTodoDialogFragment()
    }
}