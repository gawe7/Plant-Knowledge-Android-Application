package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.eartheden.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

class Category : AppCompatActivity() {
    var CateBack: ImageButton?=null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        init()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CateBack?.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun init(){
        CateBack = findViewById(R.id.category_backbtn)
    }
}