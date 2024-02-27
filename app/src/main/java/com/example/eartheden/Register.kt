package com.example.eartheden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private lateinit var register_button_back: ImageButton
    private lateinit var register_button_account: Button
    private lateinit var register_edit_user: EditText
    private lateinit var register_edit_email: EditText
    private lateinit var register_edit_password: EditText
    private lateinit var register_edit_repassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")

        init()

        if (mAuth.currentUser != null) {
            startActivity(Intent(this@Register, MainActivity::class.java))
            finish()
        }

        register_button_account.setOnClickListener {
            val user = register_edit_user.text.toString().trim()
            val pass = register_edit_password.text.toString().trim()
            val email = register_edit_email.text.toString().trim()

            if (validateUser(user) && validateEmail(email) && validatePassword(pass) && validateCpassword(pass)) {
                mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userId = mAuth.currentUser?.uid
                            val databaseReference = database.getReference("Account").child(email.replace(".", ","))
                            val userData = hashMapOf(
                                "User name" to user,
                                "Email" to email
                            )
                            userId?.let { uid ->
                                databaseReference.setValue(userData)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Create account successfully!", Toast.LENGTH_LONG).show()
                                        Log.d(TAG, "Create account successfully!")
                                        startActivity(Intent(this@Register, MainActivity::class.java))
                                        finish()
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(TAG, "Error writing user data", e)
                                        Toast.makeText(this, "Error creating account", Toast.LENGTH_LONG).show()
                                    }
                            }
                        } else {
                            Toast.makeText(this, "Authentication failed: " + task.exception?.message, Toast.LENGTH_LONG).show()
                            Log.e(TAG, "Authentication failed: ", task.exception)
                        }
                    }
            }
        }

        register_button_back.setOnClickListener { onBackPressed() }
    }

    private fun validateUser(user: String): Boolean {
        return if (user.length in 5..20) {
            true
        } else {
            register_edit_user.error = "Enter between 5 and 20 characters"
            false
        }
    }

    private fun validateCpassword(password: String): Boolean {
        val cPass = register_edit_repassword.text.toString().trim()
        return if (password == cPass) {
            true
        } else {
            register_edit_repassword.error = "Passwords do not match"
            false
        }
    }

    private fun validatePassword(password: String): Boolean {
        return if (password.length >= 6) {
            true
        } else {
            register_edit_password.error = "Password must be at least 6 characters"
            false
        }
    }

    private fun validateEmail(email: String): Boolean {
        val checkEmail1 = "[a-zA-Z0-9._-]+@gmail.com"
        val checkEmail2 = "[a-zA-Z0-9._-]+@hotmail.th"
        val checkEmail3 = "[a-zA-Z0-9._-]+@kkumail.com"

        return if (email.matches(checkEmail1.toRegex()) || email.matches(checkEmail2.toRegex()) || email.matches(checkEmail3.toRegex())) {
            true
        } else {
            register_edit_email.error = "Invalid email"
            false
        }
    }

    private fun init() {
        register_button_account = findViewById(R.id.register_submitbtn)
        register_edit_user = findViewById(R.id.register_usernametxt)
        register_edit_email = findViewById(R.id.register_emailtxt)
        register_edit_password = findViewById(R.id.register_passtxt)
        register_edit_repassword = findViewById(R.id.register_confirmpasstxt)
        register_button_back = findViewById(R.id.register_backbtn)
    }

    companion object {
        private const val TAG = "Register"
    }
}
