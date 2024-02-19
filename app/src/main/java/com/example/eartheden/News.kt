package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class News : AppCompatActivity() {
    var newsback:ImageButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        init()
        newsback?.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun init() {
        newsback = findViewById(R.id.news_backbtn)
    }
}