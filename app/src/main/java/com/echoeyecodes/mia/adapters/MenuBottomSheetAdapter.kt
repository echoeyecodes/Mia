package com.echoeyecodes.jinx.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class MenuBottomSheetAdapter(itemCallback: DefaultItemCallBack, private val context:Context) : ListAdapter<String, MenuBottomSheetAdapter.ListViewHolder>(itemCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false)
                return ListViewHolder(view)
    }


    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    }


    inner class ListViewHolder(view:View) : RecyclerView.ViewHolder(view){

    }
}