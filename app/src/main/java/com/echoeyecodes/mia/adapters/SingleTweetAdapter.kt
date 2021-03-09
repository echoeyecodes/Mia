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

class SingleTweetAdapter(private val context:Context, itemCallback:DefaultItemCallBack) : ListAdapter<String, SingleTweetAdapter.QuoteViewHolder>(itemCallback) {

    companion object {
        const val MAIN_LAYOUT = 0
        const val REGULAR_LAYOUT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = if(viewType == MAIN_LAYOUT){
            LayoutInflater.from(parent.context).inflate(R.layout.layout_tweet_item, parent, false)
        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.layout_reply_item, parent, false)
        }
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> MAIN_LAYOUT
            else -> REGULAR_LAYOUT
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class QuoteViewHolder(view:View) : RecyclerView.ViewHolder(view)
}