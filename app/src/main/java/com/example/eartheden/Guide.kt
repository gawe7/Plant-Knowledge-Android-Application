package com.example.eartheden

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Guide : AppCompatActivity() {
    private lateinit var guide_backbtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        supportActionBar?.hide()
        init()

        // Set up back button click listener
        guide_backbtn?.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
    }
    private fun init() {
        guide_backbtn = findViewById(R.id.guide_backbtn)
    }
}
