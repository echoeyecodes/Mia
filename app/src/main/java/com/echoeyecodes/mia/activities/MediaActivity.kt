package com.echoeyecodes.mia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.adapters.MediaAdapter
import com.echoeyecodes.mia.adapters.TweetAdapter
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class MediaActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        viewPager = findViewById(R.id.view_pager)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val adapter = MediaAdapter(DefaultItemCallBack())
        viewPager.adapter = adapter
    }
}