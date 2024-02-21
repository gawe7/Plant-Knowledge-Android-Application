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

class Register : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Register"
    var regisEmail: EditText? = null
    var regisPass: EditText? = null
    var createAcc : Button? = null
    var backR : ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)
        init()
        mAuth = FirebaseAuth.getInstance()

        regisEmail = findViewById(R.id.register_emailtxt)
        regisPass = findViewById(R.id.register_passtxt)

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@Register,
                MainActivity::class.java))
            finish()
        }
        createAcc?.setOnClickListener {
            val email = regisEmail?.text.toString().trim { it <= ' ' }
            val password = regisPass?.text.toString().trim { it <= ' ' }

//ทําการตรวจสอบก่อนว่ามีข้อมูลหรือไม่
            if (email.isEmpty()) {
                Toast.makeText(this,"Please enter your email address.",
                    Toast.LENGTH_LONG).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this,"Please enter your password.",Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }
//กรณีที5มีข้อมูล จะทําการตรวจสอบเงื5อนไขอื5น ๆ ก่อนทําการ create user
            mAuth!!.createUserWithEmailAndPassword(email,
                password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) { // ตรวจสอบความยาวของ password
                        Toast.makeText(this,"Password too short! Please enter minimum 6 characters.",Toast.LENGTH_LONG).show()
                                Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(this,"Authentication Failed: " +
                                task.exception!!.message,Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Authentication Failed: " +
                                task.exception!!.message)
                    }
                } else {
                    Toast.makeText(this,"Create account successfully!",Toast.LENGTH_LONG).show()
                            Log.d(TAG, "Create account successfully!")
                    startActivity(Intent(this@Register,
                        MainActivity::class.java))
                    finish()
                }
            }
        }

//กรณีกดปุ่ ม Back
        backR?.setOnClickListener { onBackPressed() }
    }
    fun init(){
        regisEmail = findViewById(R.id.register_usernametxt)
        regisPass = findViewById(R.id.register_passtxt)
        createAcc = findViewById(R.id.register_submitbtn)
        backR = findViewById(R.id.register_backbtn)
    }
}
