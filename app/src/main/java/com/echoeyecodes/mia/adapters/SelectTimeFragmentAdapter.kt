package com.echoeyecodes.jinx.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel
import com.echoeyecodes.jinx.utils.CustomDateFormatter
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack
import java.time.LocalDate
import java.time.LocalTime

class SelectTimeFragmentAdapter(itemCallback: DefaultItemCallBack, private val context:Context, private val fragmentListener: CreateTaskFragmentInterface) : ListAdapter<String, SelectTimeFragmentAdapter.SelectTimeFragmentViewHolder>(itemCallback) {

    private val dateFormatter  = CustomDateFormatter()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectTimeFragmentViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_date_item, parent, false)
                return SelectTimeFragmentViewHolder(view)
        }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: SelectTimeFragmentViewHolder, position: Int) {
            holder.bindValues(position)
    }

    inner class SelectTimeFragmentViewHolder(view:View) : RecyclerView.ViewHolder(view){
        private val textView: TextView = view.findViewById(R.id.date_item_text)
        private val timeValue: TextView = view.findViewById(R.id.date_item_text_value)
        private val container: ConstraintLayout = view.findViewById(R.id.date_item_container)
        private var localDate: LocalDate = LocalDate.now()
        private var localTime: LocalTime = LocalTime.now()

        private fun onClick(){
            fragmentListener.setIgnoreDateTime(false)
            val date = TaskDateModel(localDate.dayOfMonth, localDate.monthValue, localDate.year)
            val time = TaskTimeModel(localTime.hour, localTime.minute)
            fragmentListener.onDefaultDateTimeSelected(date, time)
        }

        @SuppressLint("SetTextI18n")
        fun bindValues(position: Int){
            when(position){
                0 ->{
                    textView.text = "Tomorrow"
                    localDate = localDate.plusDays(1)
                    timeValue.text = dateFormatter.getWeekDay(localDate.dayOfWeek.value)
                }
                1 -> {
                    textView.text = "Later this week"
                    localDate = localDate.plusDays(3)
                    timeValue.text = dateFormatter.getWeekDay(localDate.dayOfWeek.value)
                }
                2 -> {
                    textView.text = "This Weekend"
                    localDate = LocalDate.now()
                    if(localDate.dayOfWeek.value != 6){
                        val difference = 6 - localDate.dayOfWeek.value
                        localDate = localDate.plusDays(difference.toLong())
                    }
                    timeValue.text = dateFormatter.getWeekDay(localDate.dayOfWeek.value)
                }
                3 -> {
                    textView.text = "Next Week"
                    localDate = localDate.plusDays(7)
                    timeValue.text = dateFormatter.getWeekDay(localDate.dayOfWeek.value)
                }
                4 ->{
                    textView.text = "None"
                    timeValue.text = ""
                }
                5 ->{
                    textView.text = "Select Date/Time"
                    timeValue.text = ""
                }
            }

            container.setOnClickListener {
                when (position) {
                    4 -> {
                        fragmentListener.setIgnoreDateTime(true)
                    }
                    5 -> {
                        fragmentListener.openDateTimeDialog()
                    }
                    else -> onClick()
                }
            }
        }

    }
}