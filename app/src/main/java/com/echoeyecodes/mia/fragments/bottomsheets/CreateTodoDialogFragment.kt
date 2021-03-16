package com.echoeyecodes.mia.fragments.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel
import com.echoeyecodes.jinx.viewmodel.CreateTaskViewModel
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.fragments.dialogfragments.ScheduleFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.InputBottomSheetDialogStyle)
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

        selectTimeFragment = getSelectTimeFragment()
        dateBtn.setOnClickListener { selectTimeFragment?.show(childFragmentManager, SelectTimeFragment.TAG) }


        viewModel.ignoreDateTime.observe(this, {
            if (it) {
                dateBtn.text = "Unassigned"
            } else {
                dateBtn.text = viewModel.selectedDateToString()
            }
        })

        viewModel.selectedTimeLiveData.observe(this, {
            dateBtn.text = viewModel.selectedDateToString()
        })

        viewModel.selectedDateLiveData.observe(this, {
            dateBtn.text = viewModel.selectedDateToString()
        })
    }

    private fun getSelectTimeFragment(): SelectTimeFragment{
        val fragment = childFragmentManager.findFragmentByTag(SelectTimeFragment.TAG) as SelectTimeFragment?
        return fragment ?: SelectTimeFragment.newInstance()
    }

    override fun openDateTimeDialog() {
        val fragment = ScheduleFragment.newInstance()
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
    }

    companion object {
        fun newInstance(): CreateTodoDialogFragment = CreateTodoDialogFragment()
        const val TAG = "CREATE_TODO_DIALOG_FRAGMENT"
    }
}