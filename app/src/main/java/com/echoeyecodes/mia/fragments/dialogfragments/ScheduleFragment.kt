package com.echoeyecodes.mia.fragments.dialogfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.jinx.adapters.RepeatWeekAdapter
import com.echoeyecodes.mia.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel
import com.echoeyecodes.jinx.utils.DaysItemCallback
import com.echoeyecodes.mia.viewmodels.CreateTaskViewModel
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial


class ScheduleFragment() : DialogFragment() {
    private lateinit var dateTypeBtn:TextView
    private lateinit var monthDaysLayout : LinearLayout
    private lateinit var monthDaysBtn:TextView
    private lateinit var datePickerDialogFragment : DatePickerDialogFragment
    private lateinit var timePickerDialogFragment : TimePickerDialogFragment
    private lateinit var viewModel: CreateTaskViewModel
    private lateinit var weekDaysRecyclerView : RecyclerView
    private lateinit var listener: CreateTaskFragmentInterface

    companion object {
        const val TAG = "SCHEDULE_FRAGMENT"
        fun newInstance() =  ScheduleFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as CreateTaskFragmentInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomAlertDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireParentFragment()).get(CreateTaskViewModel::class.java)

        dateTypeBtn = view.findViewById(R.id.date_type_btn)
        val datePickerBtn = view.findViewById<TextView>(R.id.date_picker_btn)
        val timePickerBtn = view.findViewById<TextView>(R.id.time_picker_btn)
        val doneBtn = view.findViewById<MaterialButton>(R.id.fragment_task_time_done_btn)

        val repeatSwitch = view.findViewById<SwitchMaterial>(R.id.repeat_switch)
        val repeatContainer = view.findViewById<LinearLayout>(R.id.repeat_container)

        monthDaysLayout = view.findViewById(R.id.repeat_month_layout)
        monthDaysBtn = view.findViewById(R.id.month_picker_btn)

        initWeekRecyclerView()

        dateTypeBtn.setOnClickListener { openDateTypeList(dateTypeBtn, R.menu.menu_recurring_dates_option) }
        viewModel.shouldRepeat.observe(this, {
            repeatSwitch.isChecked = it

            if(it){
                repeatContainer.visibility = View.VISIBLE
            }else{
                repeatContainer.visibility = View.GONE
            }
        })

        val selectedDate = viewModel.selectedDateLiveData
        val selectedTime = viewModel.selectedTimeLiveData
        selectedDate.observe(this, {
            datePickerBtn.text = it.getMonth().plus(" ").plus(it.date).plus(", ").plus(it.year)
        })


        selectedTime.observe(this, {
            timePickerBtn.text = it.getFormattedTime()
        })

        viewModel.monthDate.observe(viewLifecycleOwner, {date ->
            monthDaysBtn.text = "Day $date"
        })

        viewModel.dateType.observe(viewLifecycleOwner, {

            if(it == 0){
                dateTypeBtn.text = "day"
            }

            if(it == 1){
                dateTypeBtn.text = "week"
                weekDaysRecyclerView.visibility = View.VISIBLE
            }else{
                weekDaysRecyclerView.visibility = View.GONE
            }

            if(it == 2){
                dateTypeBtn.text = "month"
                monthDaysLayout.visibility = View.VISIBLE
            }else{
                monthDaysLayout.visibility = View.GONE
            }

            if(it == 3){
                dateTypeBtn.text = "year"
            }
        })

            val date = TaskDateModel(selectedDate.value!!.date, selectedDate.value!!.month, selectedDate.value!!.year)
            val time = TaskTimeModel(selectedTime.value!!.hour, selectedTime.value!!.minute)

            datePickerDialogFragment = DatePickerDialogFragment.newInstance(date)
            timePickerDialogFragment = TimePickerDialogFragment.newInstance(time)

            datePickerBtn.setOnClickListener { openDialogFragment(childFragmentManager, datePickerDialogFragment, "DATE_PICKER_FRAGMENT") }
            timePickerBtn.setOnClickListener { openDialogFragment(childFragmentManager, timePickerDialogFragment, "TIME_PICKER_FRAGMENT") }

        monthDaysBtn.setOnClickListener { inflateMonthOptions(it, R.menu.menu_month_days) }

        repeatSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.shouldRepeat.value = isChecked
        }

        doneBtn.setOnClickListener {
            onDateTimeSelected()
        }
    }

    private fun onDateTimeSelected() {
        dismiss()
        // Collect data to task fragment, then
    }

    private fun inflateMonthOptions(view:View, menu:Int){
        val popup = PopupMenu(requireContext(), view)
        popup.menuInflater.inflate(menu, popup.menu)
        popup.show()

        popup.setOnMenuItemClickListener {
            setMonthDay(it)
            popup.dismiss()
            false
        }
    }


    private fun setMonthDay(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.day_one -> {
                viewModel.monthDate.value = 1
            }
            R.id.day_two -> {
                viewModel.monthDate.value = 2
            }
            R.id.day_three -> {
                viewModel.monthDate.value = 3
            }
            R.id.day_four -> {
                viewModel.monthDate.value = 4
            }
            R.id.day_five -> {
                viewModel.monthDate.value = 5
            }
            R.id.day_six -> {
                viewModel.monthDate.value = 6
            }
            R.id.day_seven -> {
                viewModel.monthDate.value = 7
            }
            R.id.day_eight -> {
                viewModel.monthDate.value = 8
            }
            R.id.day_nine -> {
                viewModel.monthDate.value = 9
            }
            R.id.day_ten -> {
                viewModel.monthDate.value = 10
            }
            R.id.day_eleven -> {
                viewModel.monthDate.value = 11
            }
            R.id.day_twelve -> {
                viewModel.monthDate.value = 12
            }
            R.id.day_thirteen -> {
                viewModel.monthDate.value = 13
            }
            R.id.day_fourteen -> {
                viewModel.monthDate.value = 14
            }
            R.id.day_fifteen -> {
                viewModel.monthDate.value = 15
            }
            R.id.day_sixteen -> {
                viewModel.monthDate.value = 16
            }
            R.id.day_seventeen -> {
                viewModel.monthDate.value = 17
            }
            R.id.day_eighteen -> {
                viewModel.monthDate.value = 18
            }
            R.id.day_nineteen -> {
                viewModel.monthDate.value = 19
            }
            R.id.day_twenty -> {
                viewModel.monthDate.value = 20
            }
            R.id.day_twenty_one -> {
                viewModel.monthDate.value = 21
            }
            R.id.day_twenty_two -> {
                viewModel.monthDate.value = 22
            }
            R.id.day_twenty_three -> {
                viewModel.monthDate.value = 23
            }
            R.id.day_twenty_four -> {
                viewModel.monthDate.value = 24
            }
            R.id.day_twenty_fve -> {
                viewModel.monthDate.value = 25
            }
            R.id.day_twenty_size -> {
                viewModel.monthDate.value = 26
            }
            R.id.day_twenty_seven -> {
                viewModel.monthDate.value = 27
            }
            R.id.day_twenty_eight -> {
                viewModel.monthDate.value = 28
            }
            R.id.day_twenty_nine -> {
                viewModel.monthDate.value = 29
            }
            R.id.day_thirty -> {
                viewModel.monthDate.value = 30
            }
            R.id.day_thirty_one -> {
                viewModel.monthDate.value = 31
            }
        }
    }

    private fun initWeekRecyclerView(){
        weekDaysRecyclerView = requireView().findViewById(R.id.repeat_week_layout)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val itemDecoration = CustomItemDecoration(5)
        weekDaysRecyclerView.addItemDecoration(itemDecoration)

        weekDaysRecyclerView.let {recyclerView ->

            listener?.let{ext ->
                recyclerView.layoutManager = layoutManager
                val adapter = RepeatWeekAdapter(DaysItemCallback(), ext)
                recyclerView.adapter = adapter

                viewModel.days.observe(viewLifecycleOwner, {value ->
                    adapter.submitList(ArrayList(value))
                })
            }

        }
    }

    private fun openDialogFragment(fragmentManager: FragmentManager, fragment:DialogFragment?, fragmentTag:String){
        fragment?.let {
            if(!it.isVisible){
                it.show(fragmentManager, fragmentTag)
            }
        }
    }

    private fun openDateTypeList(view:View, menu:Int){
        val popup = PopupMenu(requireContext(), view)
        popup.menuInflater.inflate(menu, popup.menu)
        popup.show()


        popup.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.day ->{
                    viewModel.dateType.value = 0
                }
                R.id.week -> {
                    viewModel.dateType.value = 1
                }
                R.id.month ->{
                    viewModel.dateType.value = 2
                }
                R.id.year ->{
                    viewModel.dateType.value = 3
                }

            }
            popup.dismiss()
            false
        }
    }
}