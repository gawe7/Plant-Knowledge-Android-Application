package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Community : AppCompatActivity() {
    var commuback:ImageButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)
        init()
        commuback?.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun init(){
        commuback = findViewById(R.id.commu_backbtn)
    }
}