package com.example.eartheden

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.databinding.ActivityAllplantBinding
import com.example.eartheden.databinding.ActivityCategoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.util.ArrayList
import java.util.Locale

class Allplant : AppCompatActivity() {
    private lateinit var binding: ActivityAllplantBinding

    lateinit var textContenttitle: TextView
    lateinit var imageViewContent: ImageView
    lateinit var textContentDetail: TextView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var content_textview_contact: TextView

    private lateinit var databaseReferenceCactus: DatabaseReference
    private lateinit var responseAllplant: MutableList<PlantModel>
    private var AllplantAdapter: Adapter? = null
    lateinit var backbtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllplantBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()


        textContenttitle = findViewById(R.id.recTitle)
        imageViewContent = findViewById(R.id.recImage)
        content_textview_contact = findViewById(R.id.recDesc)



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
                    responseAllplant.add(PlantModel())
                    AllplantAdapter!!.notifyDataSetChanged()
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
