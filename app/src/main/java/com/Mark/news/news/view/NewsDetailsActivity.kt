package com.Mark.news.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Mark.news.R

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_details)
        //Get Obj String from bundle
        val extras = intent.extras
        val objString = extras?.getString("obj")
        //initalize screen with data
//        initScreen(objString)
    }

}
