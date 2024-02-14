package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    private val TAG:String = "Profile"
    lateinit var userEmail: TextView
    lateinit var uidUser : TextView
    lateinit var singout : Button
    lateinit var backRe: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_profile)
        init()

        // ดึงค่าจาก Firebase มาใส่ใน mAuth
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser

        // นำค่ามาใส่ลงใน TextView ที่สร้างขึ้น
        userEmail?.text ="อีเมล : "+ user!!.email

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null) {
                startActivity(Intent(this@Profile, Login::class.java))
                finish()
            }
        }

        // การทำงานของปุ่ม Sign out
        singout?.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"ออกจากระบบแล้ว!", Toast.LENGTH_LONG).show()
            Log.d(TAG, "ออกจากระบบแล้ว!")
            startActivity(Intent(this@Profile, Login::class.java))
            finish()
        }

        // กรณีกดปุ่ม Back
        backRe?.setOnClickListener { onBackPressed() }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener { mAuthListener }
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener { mAuthListener }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) { moveTaskToBack(true) }
        return super.onKeyDown(keyCode, event)
    }

    fun init() {
        userEmail = findViewById(R.id.profile_emailtxt)
        uidUser = findViewById(R.id.profile_usertxt)
        singout = findViewById(R.id.profile_logout)
        backRe = findViewById(R.id.Profile_backbtn)
    }
}
