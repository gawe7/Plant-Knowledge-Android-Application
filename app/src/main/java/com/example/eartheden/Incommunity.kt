package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Incommunity : AppCompatActivity() {
    var incomback:ImageButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incommunity)
        init()
        incomback?.setOnClickListener{
            var intent = Intent(this,Community::class.java)
            startActivity(intent)
        }

    } fun init(){
        incomback = findViewById(R.id.incom_backbtn)
    }
}