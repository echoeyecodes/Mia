package com.echoeyecodes.mia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.adapters.TweetAdapter
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class TweetActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        recyclerView = findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = CustomItemDecoration(10, 15)

        //only one item should be submitted to this adapter sin'ce it details full details for a single
        // tweet
        val adapter = TweetAdapter(this, DefaultItemCallBack())

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = adapter
    }
}