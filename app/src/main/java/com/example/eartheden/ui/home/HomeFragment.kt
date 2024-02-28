package com.example.eartheden.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.CateAdapter
import com.example.eartheden.CateModel
import com.example.eartheden.Category
import com.example.eartheden.R

import com.example.eartheden.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewHome: RecyclerView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReferenceCactus: DatabaseReference
    private lateinit var responseHome: MutableList<CateModel>
    private var adapter: CateAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerViewHome = binding.homeRecycleview
        recyclerViewHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")
        databaseReferenceCactus = database.getReference("cactus")

        responseHome = mutableListOf()
        adapter = CateAdapter(responseHome as ArrayList<CateModel>)
        recyclerViewHome.adapter = adapter

        onBindingFirebase()

        val cate = binding.homeCategory
        cate.setOnClickListener {
            val intent = Intent(requireContext(), Category::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }


    private fun onBindingFirebase() {
        databaseReferenceCactus.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseHome.add(snapshot.getValue(CateModel::class.java)!!)
                adapter?.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}



//        val signoutButton = root.findViewById<Button>(R.id.home_accountbtn)
//        signoutButton.setOnClickListener {
//            val intent = Intent(requireContext(), Login::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }
//
//        val profile = root.findViewById<Button>(R.id.profile)
//        profile.setOnClickListener {
//            val intent = Intent(requireContext(), Profile::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }