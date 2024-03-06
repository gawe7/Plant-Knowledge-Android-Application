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
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private val TAG:String = "Result Activity"
    var userEmail: TextView? = null
//    var uidUser : TextView? = null
    var singout : Button? = null
    var backRe: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_profile)
        init()

        // เรียก mAuth ก่อน
        mAuth = FirebaseAuth.getInstance()
        // ดึงข้อมูลผู้ใช้
        val user = mAuth!!.currentUser
        // นำข้อมูลผู้ใช้มาแสดงใน TextView
//        uidUser?.text = "User : " + user!!.uid
        userEmail?.text ="Email : "+ user!!.email

        // เพิ่ม AuthStateListener
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
            Toast.makeText(this,"Signed out!", Toast.LENGTH_LONG).show()
            Log.d(TAG, "Signed out!")
            startActivity(Intent(this@Profile,Login::class.java))
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
//        uidUser = findViewById(R.id.profile_usertxt)
        singout = findViewById(R.id.profile_setting)
        backRe = findViewById(R.id.profile_backbtn)
    }
}