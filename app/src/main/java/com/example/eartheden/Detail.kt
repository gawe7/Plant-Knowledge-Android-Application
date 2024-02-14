package com.example.eartheden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)
    }
}