package com.example.eartheden

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton // เพิ่ม import สำหรับ ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.Category
import com.example.eartheden.R
import com.example.eartheden.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

  //  private lateinit var recyclerViewHome:RecyclerView
    //private lateinit var mAuth: FirebaseAuth
  //  private lateinit var database: FirebaseDatabase
   // private lateinit var databaseReferenceCactus: DatabaseReference
   // private lateinit var responseHome:MutableList<PlantModel>
   // private var HomeAdapter:Adapter? = null
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        /*mAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")

        recyclerViewHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        databaseReferenceCactus = database.getReference("cactus")
        responseHome = mutableListOf()
        HomeAdapter = Adapter(responseHome as ArrayList<PlantModel>)
*/

        val navView: BottomNavigationView = binding.navView

        val main_catebtn: ImageButton = findViewById(R.id.main_catebtn)

        main_catebtn.setOnClickListener {
            var intent = Intent(this, Category::class.java)
            startActivity(intent)
        }

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_menu
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
   /* private fun onBindingFirebase() {
        databaseReferenceCactus.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseHome.add(snapshot.getValue(PlantModel::class.java)!!)
                HomeAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}
        })
    }*/
}
