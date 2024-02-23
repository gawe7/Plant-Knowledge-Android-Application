package com.example.eartheden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.databinding.ActivityCategoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Category : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

    private lateinit var recyclerViewCate: RecyclerView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReferenceCactus: DatabaseReference
    private lateinit var responseCate: MutableList<CateModel>
    private var cateAdapter: CateAdapter? = null
    private var category_backbtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        init()



        // Firebase
        mAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")


        recyclerViewCate.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        databaseReferenceCactus = database.getReference("cactus")
        responseCate = mutableListOf()
        cateAdapter = CateAdapter(responseCate as ArrayList<CateModel>)
        recyclerViewCate.adapter = cateAdapter
        onBindingFirebase()



        category_backbtn?.setOnClickListener { onBackPressed() }
    }




    private fun onBindingFirebase() {
        databaseReferenceCactus.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseCate.add(snapshot.getValue(CateModel::class.java)!!)
                cateAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun init() {
        category_backbtn = findViewById(R.id.category_backbtn)
        recyclerViewCate = findViewById(R.id.recyclerViewCate)

    }
}
