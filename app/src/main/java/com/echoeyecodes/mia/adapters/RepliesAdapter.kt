package com.echoeyecodes.mia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class RepliesAdapter(itemCallback:DefaultItemCallBack) : ListAdapter<String, RepliesAdapter.QuoteViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_reply_item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class QuoteViewHolder(view:View) : RecyclerView.ViewHolder(view)
}