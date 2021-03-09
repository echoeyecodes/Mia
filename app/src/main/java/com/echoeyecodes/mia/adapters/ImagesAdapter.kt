package com.echoeyecodes.mia.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack
import com.echoeyecodes.mia.utils.convertToDp
import com.echoeyecodes.mia.utils.getScreenSize

class ImagesAdapter(itemCallback: DefaultItemCallBack, private val margin: Int) : ListAdapter<String, ImagesAdapter.QuoteViewHolder>(itemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
            holder.bind(position)
    }

    override fun getItemCount(): Int {
        // return ArrayList(currentList.subList(0, 1)).size (Array list is used to safely create the sublist
        // in case the items are less than 1, then it returns only the first 2 items no matter the size)
        return 2
    }

    @SuppressLint("SetTextI18n")
    inner class QuoteViewHolder(view:View) : RecyclerView.ViewHolder(view){
        private val imageContainer = view.findViewById<FrameLayout>(R.id.image_container)
        private val overlay = view.findViewById<RelativeLayout>(R.id.extra_image_overlay)
        private val overlayText = view.findViewById<TextView>(R.id.overlay_text)

        init {
            val layoutParams = imageContainer.layoutParams as RecyclerView.LayoutParams

            layoutParams.width = (getScreenSize().width / itemCount) - (margin*4).convertToDp()
            layoutParams.height = 200.convertToDp()
            overlayText.text = "+$itemCount other media files"

            imageContainer.layoutParams = layoutParams
        }

        fun bind(position: Int){
            if(position == 1 && itemCount > 2){
                overlay.visibility = View.VISIBLE
            }else{
                overlay.visibility = View.GONE
            }
        }
    }
}