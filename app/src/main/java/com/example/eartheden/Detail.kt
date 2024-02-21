package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.eartheden.ui.dashboard.DashboardFragment

class Detail : AppCompatActivity() {
    var backbtn:ImageButton?=null
    var bkbtn:ImageButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)
        init()
        backbtn?.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun init() {
        backbtn = findViewById(R.id.detail_backbtn)

    }
}