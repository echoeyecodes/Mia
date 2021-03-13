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
    private lateinit var dialogView:View
    var popup: PopupMenu? = null
    private lateinit var dateTypeBtn:TextView
    private var selectTimeFragment : SelectTimeFragment? = null
    private var datePickerDialogFragment : DatePickerDialogFragment? = null
    private var timePickerDialogFragment : TimePickerDialogFragment? = null

    private var weekDaysRecyclerView : RecyclerView? = null
    private var monthDaysLayout : LinearLayout? = null
    private var monthDaysBtn:TextView? = null

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

        selectTimeFragment = SelectTimeFragment.newInstance(this)

        dateBtn.setOnClickListener { openDialogFragment(childFragmentManager, selectTimeFragment, "SELECT_TIME_FRAGMENT") }

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


    @SuppressLint("SetTextI18n")
    override fun openDateTimeDialog(){
        dismissSelectTimeFragment()
        setIgnoreDateTime(false)
        val inflater = (requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)) as LayoutInflater

        dialogView = inflater.inflate(R.layout.fragment_choose_date, null)

        val builder = AlertDialog.Builder(requireContext()).setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()

        dateTypeBtn = dialogView.findViewById(R.id.date_type_btn)
        val datePickerBtn = dialogView.findViewById<TextView>(R.id.date_picker_btn)
        val timePickerBtn = dialogView.findViewById<TextView>(R.id.time_picker_btn)
        val cancelBtn = dialogView.findViewById<MaterialButton>(R.id.fragment_task_time_cancel_btn)
        val doneBtn = dialogView.findViewById<MaterialButton>(R.id.fragment_task_time_done_btn)

        val repeatSwitch = dialogView.findViewById<SwitchMaterial>(R.id.repeat_switch)
        val repeatContainer = dialogView.findViewById<LinearLayout>(R.id.repeat_container)

        monthDaysLayout = dialogView.findViewById(R.id.repeat_month_layout)
        monthDaysBtn = dialogView.findViewById(R.id.month_picker_btn)

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
            monthDaysBtn?.let {
                it.text = "Day $date"
            }
        })

        viewModel.dateType.observe(viewLifecycleOwner, {

            if(it == 0){
                dateTypeBtn.text = "day"
            }

            if(it == 1){
                dateTypeBtn.text = "week"
                weekDaysRecyclerView?.visibility = View.VISIBLE
            }else{
                weekDaysRecyclerView?.visibility = View.GONE
            }

            if(it == 2){
                dateTypeBtn.text = "month"
                monthDaysLayout?.visibility = View.VISIBLE
            }else{
                monthDaysLayout?.visibility = View.GONE
            }

            if(it == 3){
                dateTypeBtn.text = "year"
            }
        })

        val date = TaskDateModel(selectedDate.value!!.date, selectedDate.value!!.month, selectedDate.value!!.year)
        val time = TaskTimeModel(selectedTime.value!!.hour, selectedTime.value!!.minute)

        datePickerDialogFragment = DatePickerDialogFragment( date, this)
        timePickerDialogFragment = TimePickerDialogFragment(time, this)

        datePickerBtn.setOnClickListener { openDialogFragment(childFragmentManager, datePickerDialogFragment, "DATE_PICKER_FRAGMENT") }
        timePickerBtn.setOnClickListener { openDialogFragment(childFragmentManager, timePickerDialogFragment, "TIME_PICKER_FRAGMENT") }

        monthDaysBtn?.setOnClickListener { inflateMonthOptions(it, R.menu.menu_month_days) }

        repeatSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.shouldRepeat.value = isChecked
        }

        cancelBtn.setOnClickListener { alertDialog.dismiss() }
        doneBtn.setOnClickListener {
            alertDialog.dismiss()
            onDateTimeSelected()
        }
    }

    override fun onDateSelected(date: TaskDateModel) {
        viewModel.selectedDateLiveData.value = date
    }

    private fun initWeekRecyclerView(){
        weekDaysRecyclerView = dialogView.findViewById(R.id.repeat_week_layout)
        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexWrap = FlexWrap.NOWRAP
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        layoutManager.alignItems = AlignItems.CENTER

        weekDaysRecyclerView?.let {recyclerView ->
            recyclerView.layoutManager = layoutManager
            val adapter = RepeatWeekAdapter(DaysItemCallback(), requireContext(), this)
            recyclerView.adapter = adapter

            viewModel.days.observe(viewLifecycleOwner, {value ->
                adapter.submitList(value)
            })
        }
    }


    override fun onTimeSelected(time: TaskTimeModel) {
        viewModel.selectedTimeLiveData.value = time
    }

    private fun openDialogFragment(fragmentManager:FragmentManager, fragment:DialogFragment?, fragmentTag:String){
        fragment?.let {
            if(!it.isVisible){
                it.show(fragmentManager, fragmentTag)
            }
        }
    }


    override fun setDateType(idx: Int) {
        viewModel.setSelectedDays(idx)
    }

    private fun dismissSelectTimeFragment(){
        ((childFragmentManager.findFragmentByTag("SELECT_TIME_FRAGMENT")) as SelectTimeFragment?)?.dismiss()
    }

    override fun onDefaultDateTimeSelected(date:TaskDateModel, time: TaskTimeModel){
        also {
            onDateSelected(date)
            onTimeSelected(time)
            selectTimeFragment?.dismiss()
        }
    }

    override fun setIgnoreDateTime(value: Boolean) {
        viewModel.ignoreDateTime.value = value
        selectTimeFragment?.dismiss()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun onDateTimeSelected() {
        // Collect data to task fragment, then
    }


    private fun openDateTypeList(view:View, menu:Int){
        popup = PopupMenu(requireContext(), view)
        popup!!.menuInflater.inflate(menu, popup!!.menu)
        popup!!.show()


        popup!!.setOnMenuItemClickListener {
            setDateType(it)
            popup!!.dismiss()
            false
        }
    }

    private fun inflateMonthOptions(view:View, menu:Int){
        popup = PopupMenu(requireContext(), view)
        popup!!.menuInflater.inflate(menu, popup!!.menu)
        popup!!.show()

        popup!!.setOnMenuItemClickListener {
            setMonthDay(it)
            popup!!.dismiss()
            false
        }
    }

    private fun setMonthDay(menuItem: MenuItem){

        when(menuItem.itemId){
            R.id.day_one ->{
                viewModel.monthDate.value = 1
            }
            R.id.day_two ->{
                viewModel.monthDate.value = 2
            }
            R.id.day_three ->{
                viewModel.monthDate.value = 3
            }
            R.id.day_four ->{
                viewModel.monthDate.value = 4
            }
            R.id.day_five ->{
                viewModel.monthDate.value = 5
            }
            R.id.day_six ->{
                viewModel.monthDate.value = 6
            }
            R.id.day_seven ->{
                viewModel.monthDate.value = 7
            }
            R.id.day_eight ->{
                viewModel.monthDate.value = 8
            }
            R.id.day_nine ->{
                viewModel.monthDate.value = 9
            }
            R.id.day_ten ->{
                viewModel.monthDate.value = 10
            }
            R.id.day_eleven ->{
                viewModel.monthDate.value = 11
            }
            R.id.day_twelve ->{
                viewModel.monthDate.value = 12
            }
            R.id.day_thirteen ->{
                viewModel.monthDate.value = 13
            }
            R.id.day_fourteen ->{
                viewModel.monthDate.value = 14
            }
            R.id.day_fifteen ->{
                viewModel.monthDate.value = 15
            }
            R.id.day_sixteen ->{
                viewModel.monthDate.value = 16
            }
            R.id.day_seventeen ->{
                viewModel.monthDate.value = 17
            }
            R.id.day_eighteen ->{
                viewModel.monthDate.value = 18
            }
            R.id.day_nineteen ->{
                viewModel.monthDate.value = 19
            }
            R.id.day_twenty ->{
                viewModel.monthDate.value = 20
            }
            R.id.day_twenty_one ->{
                viewModel.monthDate.value = 21
            }
            R.id.day_twenty_two ->{
                viewModel.monthDate.value = 22
            }
            R.id.day_twenty_three ->{
                viewModel.monthDate.value = 23
            }
            R.id.day_twenty_four ->{
                viewModel.monthDate.value = 24
            }
            R.id.day_twenty_fve ->{
                viewModel.monthDate.value = 25
            }
            R.id.day_twenty_size ->{
                viewModel.monthDate.value = 26
            }
            R.id.day_twenty_seven ->{
                viewModel.monthDate.value = 27
            }
            R.id.day_twenty_eight ->{
                viewModel.monthDate.value = 28
            }
            R.id.day_twenty_nine ->{
                viewModel.monthDate.value = 29
            }
            R.id.day_thirty ->{
                viewModel.monthDate.value = 30
            }
            R.id.day_thirty_one ->{
                viewModel.monthDate.value = 31
            }
        }
    }

    private fun setDateType(menuItem: MenuItem){
        when(menuItem.itemId){
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
        popup?.dismiss()
    }

    companion object {
        fun newInstance(): CreateTodoDialogFragment = CreateTodoDialogFragment()
    }
}