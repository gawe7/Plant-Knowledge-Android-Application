package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private val TAG: String = "ForgotPassword"
    private var FgP_Email: EditText? = null
    private var forgot_submit: Button? = null
    private var forgot_back: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_forgot_password)
        init()

        mAuth = FirebaseAuth.getInstance()

        forgot_submit?.setOnClickListener {
            val email = FgP_Email?.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                        Toast.makeText(this, "An email has been sent to reset your password.", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@ForgotPassword, Login::class.java))
                        finish()
                    } else {
                        Log.w(TAG, "sendPasswordResetEmail:failure", task.exception)
                        Toast.makeText(this, "Failed to send reset email. Please try again later.", Toast.LENGTH_LONG).show()
                    }
                }
        }

        forgot_back?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun init() {
        FgP_Email = findViewById(R.id.forgot_emailtxt)
        forgot_submit = findViewById(R.id.forgot_submitbtn)
        forgot_back = findViewById(R.id.forgot_backbtn)
    }
}
