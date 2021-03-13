package com.echoeyecodes.mia.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class TodoItemAdapter(itemCallback:DefaultItemCallBack) : ListAdapter<String, TodoItemViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_todo_item, parent, false)
        return TodoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
            when(position){
                0 -> holder.bind(2)
                1 -> holder.bind(0)
                2 -> holder.bind(1)
                3 -> holder.bind(2)
                4 -> holder.bind(1)
            }
    }

    override fun getItemCount(): Int {
        return 5
    }
}

 class TodoItemViewHolder(view:View) : RecyclerView.ViewHolder(view){
     private val recyclerView = view.findViewById<RecyclerView>(R.id.sub_task_recycler_view)

     init {
         val layoutManager = LinearLayoutManager(recyclerView.context)
         val itemDecoration = CustomItemDecoration(0, 0)

         recyclerView.layoutManager = layoutManager
         recyclerView.addItemDecoration(itemDecoration)
     }

     fun bind(count:Int){
         if(count == 0){
             recyclerView.visibility = View.GONE
         }else{
             recyclerView.visibility = View.VISIBLE
         }

         val adapter = TodoSubTaskAdapter(count, DefaultItemCallBack())
         recyclerView.adapter = adapter
     }

}