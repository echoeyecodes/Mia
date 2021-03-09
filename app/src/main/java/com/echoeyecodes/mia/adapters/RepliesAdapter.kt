package com.echoeyecodes.mia.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class RepliesAdapter(private val context:Context, itemCallback:DefaultItemCallBack) : ListAdapter<String, RepliesViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepliesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_reply_item, parent, false)
        return RepliesViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: RepliesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }
}

class RepliesViewHolder(context:Context, view:View) : BaseTweetViewHolder(context,view){

    init {
        imagesRecyclerView.visibility = View.GONE // hiding the images list in replies for now
    }
}