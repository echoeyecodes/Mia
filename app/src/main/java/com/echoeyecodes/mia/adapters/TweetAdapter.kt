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

class TweetAdapter(private val context:Context, itemCallback:DefaultItemCallBack) : ListAdapter<String, TweetViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_tweet_item, parent, false)
        return TweetViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }
}

abstract class BaseTweetViewHolder(context: Context, view:View) : RecyclerView.ViewHolder(view){
    val imagesRecyclerView = view.findViewById<RecyclerView>(R.id.images_recycler_view)

    init{
        val layoutManager = FlexboxLayoutManager(context,FlexDirection.ROW, FlexWrap.WRAP)

        val margin = 10
        imagesRecyclerView.layoutManager = layoutManager
        val itemDecoration = CustomItemDecoration(margin)

        imagesRecyclerView.addItemDecoration(itemDecoration)
        val adapter = ImagesAdapter(DefaultItemCallBack(), margin)
        imagesRecyclerView.adapter = adapter
    }
}

 class TweetViewHolder(context:Context, view:View) : BaseTweetViewHolder(context, view){
    private val recyclerView = view.findViewById<RecyclerView>(R.id.replies_recycler_view)

    init {
        val layoutManager = LinearLayoutManager(context)
        val adapter = RepliesAdapter(context, DefaultItemCallBack())
        val itemDecoration = CustomItemDecoration(5, 0)

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = adapter

    }
}