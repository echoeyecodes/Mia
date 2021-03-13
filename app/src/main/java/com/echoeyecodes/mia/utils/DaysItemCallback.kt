package com.echoeyecodes.jinx.utils

import androidx.recyclerview.widget.DiffUtil

class DaysItemCallback : DiffUtil.ItemCallback<DaysModel>() {
    override fun areItemsTheSame(oldItem: DaysModel, newItem: DaysModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DaysModel, newItem: DaysModel): Boolean {
        return oldItem.selected == newItem.selected
    }
}