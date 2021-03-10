package com.echoeyecodes.mia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.adapters.TweetAdapter
import com.echoeyecodes.mia.fragments.dialogframents.AddTweetDialogFragment
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = CustomItemDecoration(10, 15)
        val adapter = TweetAdapter(this, DefaultItemCallBack())

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = adapter

        showAddTweetDialog()
    }

    private fun showAddTweetDialog(){
        val fragment = AddTweetDialogFragment()
        fragment.show(supportFragmentManager, "ADD_TWEET_DIALOG_FRAGMENT")
    }
}