package com.example.eartheden

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private val TAG: String = "Register"

    private var register_button_back: ImageButton? = null
    private var register_button_account: Button? = null
    private var register_edit_user: EditText? = null
    private var register_edit_email: EditText? = null
    private var register_edit_password: EditText? = null
    private var register_edit_repassword: EditText? = null
    private var register_text: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)
        init()
        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@Register, MainActivity::class.java))
            finish()
        }

        register_button_account?.setOnClickListener {
            val user: String = register_edit_user!!.text.toString().trim()
            val pass: String = register_edit_password!!.text.toString().trim()
            val email: String = register_edit_email!!.text.toString().trim()
            val database = Firebase.database

            if (validateUser() && validateEmail() && validatePassword() && validateCpassword()) {
                val tempMail: String = email
                val mark = '.'
                var newMail = ""
                for (char in tempMail) {
                    if (char != mark) {
                        newMail += char
                    }
                }

                val databaseReference = database.reference.child("Account").child(newMail)
                databaseReference.child("User name").setValue(user)
                databaseReference.child("Password").setValue(pass)
                databaseReference.child("Email").setValue(email)
                Toast.makeText(this, "Create account successfully!", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Create account successfully!")

                // นำผู้ใช้ไปยัง MainActivity หลังจากการสมัครเสร็จสมบูรณ์
                startActivity(Intent(this@Register, MainActivity::class.java))
                finish()
            }
        }

        register_button_back?.setOnClickListener { onBackPressed() }
    }

    private fun createEmail(): Boolean {
        val pass: String = register_edit_password!!.text.toString().trim()
        val email: String = register_edit_email!!.text.toString().trim()

        mAuth!!.createUserWithEmailAndPassword(email, pass)
        return true
    }

    private fun validateUser(): Boolean {
        val user = register_edit_user?.text.toString().trim()
        return if (user.length in 5..20) {
            true
        } else {
            register_edit_user?.setError("กรอกให้มากกว่า 5 ตัวอักษรแต่ไม่เกิน 20 ตัวอักษร")
            false
        }
    }

    private fun validateCpassword(): Boolean {
        val uPass = register_edit_password?.text.toString().trim()
        val cPass = register_edit_repassword?.text.toString().trim()
        return if (uPass != cPass) {
            register_edit_repassword?.setError("รหัสไม่ตรงกัน")
            false
        } else if (cPass.isEmpty()) {
            register_edit_repassword?.setError("field can not be empty")
            false
        } else {
            true
        }
    }

    private fun validatePassword(): Boolean {
        val uPass = register_edit_password!!.text.toString().trim()
        return if (uPass.isEmpty()) {
            register_edit_password?.setError("field can not be empty")
            false
        } else if (uPass.length < 6) {
            register_edit_password?.setError("ความยาวต้องมากกว่า 6 ตัว")
            false
        } else {
            register_edit_password?.setError(null)
            true
        }
    }

    private fun validateEmail(): Boolean {
        val userEmail = register_edit_email?.text.toString().trim()
        val checkEmail1 = "[a-zA-Z0-9._-]+@gmail.com"
        val checkEmail2 = "[a-zA-Z0-9._-]+@hotmail.th"
        val checkEmail3 = "[a-zA-Z0-9._-]+@kkumail.com"
        return if (userEmail.isEmpty()) {
            register_edit_email?.setError("Field can not be empty")
            false
        } else if (!userEmail.matches(checkEmail1.toRegex()) && !userEmail.matches(checkEmail2.toRegex()) && !userEmail.matches(checkEmail3.toRegex())) {
            register_edit_email?.setError("Invalid Email!")
            false
        } else {
            true
        }
    }

    private fun init() {
        register_button_account = findViewById(R.id.register_submitbtn)
        register_edit_user = findViewById(R.id.register_usernametxt)
        register_edit_email = findViewById(R.id.register_emailtxt)
        register_edit_password = findViewById(R.id.register_passtxt)
        register_edit_repassword = findViewById(R.id.register_confirmpasstxt)
        register_button_back = findViewById(R.id.register_backbtn)
        register_text = findViewById(R.id.register_regtxt)
    }
}
