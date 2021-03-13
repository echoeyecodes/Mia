package com.echoeyecodes.mia.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.echoeyecodes.mia.utils.DefaultItemCallBack
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class TodoAdapter(itemCallback:DefaultItemCallBack) : ListAdapter<String, TodoViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_view_with_header, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }
}

 class TodoViewHolder(view:View) : RecyclerView.ViewHolder(view){
        private val textView = view.findViewById<TextView>(R.id.header_text)
        private val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

     init {
         val layoutManager = LinearLayoutManager(recyclerView.context)
         val itemDecoration = CustomItemDecoration(10, 0)

         recyclerView.layoutManager = layoutManager
         recyclerView.addItemDecoration(itemDecoration)

         val adapter = TodoItemAdapter(DefaultItemCallBack())
         recyclerView.adapter = adapter
     }
}