package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.eartheden.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Login Activity"

    var login_edit_email: EditText? = null
    var login_edit_password: EditText? = null
    var login_button_login: Button? = null
    var login_button_create: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@Login, MainActivity::class.java))
            finish()
        }
        login_button_login?.setOnClickListener {
            val email = login_edit_email?.text.toString().trim { it <= ' ' }
            val password = login_edit_password?.text.toString().trim { it <= ' ' }
            // ทําการตรวจสอบก่อนว่ามีข้อมูลหรือไม่ ก่อนทีจะไปตรวจสอบค่า
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

            //ทําการตรวจสอบค่าทีกรอกกับค่าจาก Firebase Authentication
            mAuth!!.signInWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener { task ->

                //กรณีทีไม่ผ่านการตรวจสอบ
                if (!task.isSuccessful) {
                    //ตรวจสอบความยาวของ password ว่าน้อยกว่า 6 ไหม
                    if (password.length < 6) {
                        login_edit_password?.error =
                            "Please check your password.Password must have minimum 6 characters."
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(
                            this,
                            "Authentication Failed: " + task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Authentication Failed: " + task.exception!!.message)
                    }
                } else { //กรณีที5อีเมลและรหัสถูกต้อง
                    Toast.makeText(this, "Sign in successfully!", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Sign in successfully!")
                    startActivity(Intent(this@Login, MainActivity::class.java))
                    finish()
                }
            }
        }

        //กรณีกดปุ่ ม create account
        login_button_create?.setOnClickListener {
            startActivity(Intent(this@Login, Register::class.java))
        }

    }
    fun init() {
        login_edit_email = findViewById(R.id.login_emailtxt)
        login_edit_password = findViewById(R.id.login_passwordtxt)
        login_button_login = findViewById(R.id.login_loginBtn)
        login_button_create = findViewById(R.id.login_registerBtn)
    }
}