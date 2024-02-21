package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    private val TAG: String = "ForgotPassword"
    var FgP_Email: EditText? = null
    var forgot_submit : Button? = null
    var forgot_back : ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_forgot_password)
        init()
        mAuth = FirebaseAuth.getInstance()
        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@ForgotPassword, MainActivity::class.java))
            finish()
        }
        forgot_submit?.setOnClickListener {
            val email = FgP_Email?.text.toString().trim { it <= ' ' }


//ทําการตรวจสอบก่อนว่ามีข้อมูลหรือไม่
            if (email.isEmpty()) {
                Toast.makeText(
                    this, "Please enter your email address.",
                    Toast.LENGTH_LONG
                ).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }

//กรณีกดปุ่ ม Back

    }
        forgot_back?.setOnClickListener { onBackPressed() }
    }
    fun init(){
        FgP_Email = findViewById(R.id.forgot_emailtxt)
        forgot_submit = findViewById(R.id.forgot_submitbtn)
        forgot_back = findViewById(R.id.forgot_backbtn)
    }
}