package com.example.eartheden

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class Detail : AppCompatActivity() {

        lateinit var textContenttitle: TextView
        lateinit var imageViewContent: ImageView
        lateinit var textContentDetail: TextView
        lateinit var firebaseAuth: FirebaseAuth
        lateinit var firebaseDatabase: FirebaseDatabase
        lateinit var content_textview_contact: TextView

        private lateinit var databaseReferenceCactus: DatabaseReference
        private lateinit var responseDetail: MutableList<DetailModel>
        private var DetailAdapter: DetailAdapter? = null
        lateinit var backbtn: ImageButton
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportActionBar?.hide()
            setContentView(R.layout.activity_detail)


            textContenttitle = findViewById(R.id.detailTitle)
            imageViewContent = findViewById(R.id.detailImage)
            textContentDetail = findViewById(R.id.detailPriority)
            content_textview_contact = findViewById(R.id.detailDesc)

            firebaseAuth = FirebaseAuth.getInstance()
            firebaseDatabase = FirebaseDatabase.getInstance()

            var intent = intent
            var getkey = intent.getStringExtra("key")
            var databaseReference = firebaseDatabase.getReference("home/$getkey")

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    textContenttitle.text = snapshot.child("title").value.toString()
                    textContentDetail.text = snapshot.child("detail").value.toString()
                    Picasso.get().load(snapshot.child("Image").value.toString())
                        .error(R.drawable.imgloading)
                        .placeholder(R.drawable.imgloading)
                        .into(imageViewContent)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
            backbtn?.setOnClickListener {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        private fun onBindingFirebase() {
            databaseReferenceCactus.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val detailModel = snapshot.getValue(DetailModel::class.java)
                    detailModel?.let {
                        responseDetail.add(DetailModel())
                        DetailAdapter!!.notifyDataSetChanged()
                    }
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    // Handle onChildChanged
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    // Handle onChildRemoved
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    // Handle onChildMoved
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled
                }
            })
        }

        fun init() {
            backbtn = findViewById(R.id.detail_backbtn)

        }
    }
