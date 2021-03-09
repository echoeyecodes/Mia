package com.echoeyecodes.mia.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class MediaAdapter(itemCallback:DefaultItemCallBack) : ListAdapter<String, MediaViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_view_item, parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}

class MediaViewHolder(view:View) : RecyclerView.ViewHolder(view){

}