package com.echoeyecodes.jinx.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.utils.DaysItemCallback
import com.echoeyecodes.jinx.utils.DaysModel
import com.echoeyecodes.mia.R

class RepeatWeekAdapter(itemCallback: DaysItemCallback, private val context: Context, private val fragmentListener:CreateTaskFragmentInterface) : ListAdapter<DaysModel, RepeatWeekAdapter.RepeatWeekViewHolder>(itemCallback) {

    companion object {
        const val UNSELECTED = 0
        const val SELECTED = 1
    }

    override fun getItemViewType(position: Int): Int {
        if(getItem(position).selected){
            return SELECTED
        }
        return UNSELECTED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatWeekViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_week_recycler_view_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.repeat_week_item_text)
        when(viewType){
            SELECTED -> {
                view.background = AppCompatResources.getDrawable(context, R.drawable.drawable_week_recycler_view_selected)
                textView.setTextColor(Color.WHITE)
            }else -> {
            view.background = AppCompatResources.getDrawable(context, R.drawable.drawable_week_recycler_view)
            textView.setTextColor(Color.BLACK)
            }
        }
        return RepeatWeekViewHolder(view)
    }


    override fun onBindViewHolder(holder: RepeatWeekViewHolder, position: Int) {

        holder.textView.text = getItem(position).value
        holder.textView.setOnClickListener {
            fragmentListener.setDateType(position)
        }
    }

    inner class RepeatWeekViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.repeat_week_item_text)
    }
}