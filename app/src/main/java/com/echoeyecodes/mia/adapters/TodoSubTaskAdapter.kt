package com.echoeyecodes.mia.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.echoeyecodes.mia.utils.DefaultItemCallBack
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class TodoSubTaskAdapter(private val size:Int, itemCallback:DefaultItemCallBack) : ListAdapter<String, TodoSubTaskViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoSubTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_todo_subitem, parent, false)
        return TodoSubTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoSubTaskViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return size
    }
}

 class TodoSubTaskViewHolder(view:View) : RecyclerView.ViewHolder(view){

}