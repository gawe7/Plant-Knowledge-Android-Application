package com.example.eartheden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eartheden.databinding.ActivityDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class Detail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var textContentTitle: TextView
    private lateinit var textContentDisease: TextView
    private lateinit var imageViewContent: ImageView
    private lateinit var textContentDetail: TextView
    private lateinit var textContentCare: TextView

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private var backbtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View binding for safer and cleaner access to views
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        // Initialize views after view inflation
        textContentTitle = binding.detailTitle // Use findViewById when not using ViewBinding
        textContentDetail = binding.detailDesc
        textContentDisease = binding.detailDisease
        textContentCare = binding.detailCare
        backbtn = findViewById(R.id.detail_backbtn)
        imageViewContent = findViewById(R.id.detailImage)

        // Initialize Firebase instances
        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")

        // Get key from intent and handle potential null case
        val getkey = intent.getStringExtra("key")
        if (getkey != null) {
            // Access data from "home" and "category" sequentially
            fetchDataFromHome(getkey)
            fetchDataFromCategory(getkey)
        } else {
            Log.e("Detail", "Missing key in intent")
            // Handle the case where the key is missing (e.g., show an error message)
        }

        // Back button click listener
        backbtn?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun fetchDataFromHome(getkey: String) {
        val databaseReference = firebaseDatabase.getReference("category/$getkey").child("c0")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    textContentTitle.text = snapshot.child("title").value.toString()
                    textContentDetail.text = snapshot.child("detail").value.toString()
                    textContentDisease.text = snapshot.child("disease").value.toString()
                    textContentCare.text = snapshot.child("care").value.toString()
                    val imageUrl = snapshot.child("img").value.toString()
                    if (!imageUrl.isNullOrEmpty()) {
                        Glide.with(this@Detail)
                            .load(imageUrl)
                            .into(imageViewContent)
                    } else {
                        imageViewContent.setImageResource(R.drawable.imgloading)
                    }
                } else {
                    Log.w("Detail", "No data found in home for key: $getkey")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Detail, "Failed to read from home.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchDataFromCategory(getkey: String) {
        this.databaseReference =
            firebaseDatabase.getReference("category/$getkey")
        // ... (Implement logic for interacting with data from "category")
    }
}

  /*  if (getkey != null)
    {
        // Initialize databaseReference
        firebaseDatabase =
            FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")
        this@Detail.databaseReference =
            firebaseDatabase.getReference("category/$getkey")
    } else
    {
        // Handle case where key is not available (e.g., show error message)
        Log.e("Detail", "Missing key in intent")
    }

    // Back button click listener (move initialization to onCreate)
    backbtn = findViewById(R.id.detail_backbtn) // Replace with actual ID
    backbtn?.setOnClickListener
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }*/


/* var databaseReference = firebaseDatabase.getReference("catagory/$getkey")
 /* // rebase data retrieval
  databaseReference.addValueEventListener(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
          if (snapshot.exists()) {
              textContenttitle.text = snapshot.child("title").value.toString()
              val detailValue = snapshot.child("detail").value
              if (detailValue is String) {
                  textContentDetail.text = detailValue.toString()
              } else {
                  Log.w("Detail", "Unexpected data type for detail: ${detailValue?.javaClass}")
              }
              Picasso.get().load(snapshot.child("img").value.toString())
                  .error(R.drawable.imgloading)
                  .placeholder(R.drawable.imgloading)
                  .into(imageViewContent)
          } else {
              // Handle case where data doesn't exist at the provided key (e.g., show error message)
              Log.w("Detail", "No data found at key: $getkey")
          }
      }

      override fun onCancelled(error: DatabaseError) {
          Toast.makeText(this@Detail, "Failed to read Detail.", Toast.LENGTH_SHORT).show()
      }*/

*/



