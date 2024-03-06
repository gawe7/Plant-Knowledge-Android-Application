package com.example.eartheden.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.CateAdapter
import com.example.eartheden.CateModel
import com.example.eartheden.Category
import com.example.eartheden.databinding.FragmentDashboardBinding
import com.example.eartheden.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// MY plant
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var recyclerViewMyplant: RecyclerView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var responseMyplant: MutableList<CateModel>
    private var adapter: CateAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerViewMyplant = binding.recyclerViewMyplant
        recyclerViewMyplant.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")
        databaseReference = database.getReference("Noti")

        responseMyplant = mutableListOf()
        adapter = CateAdapter(responseMyplant as ArrayList<CateModel>)
        recyclerViewMyplant.adapter = adapter

        onBindingFirebase()

        /*val cate = binding.homeCategory
        cate.setOnClickListener {
            val intent = Intent(requireContext(), Category::class.java)
            startActivity(intent)
            requireActivity().finish()
        }*/

        return view
    }


    private fun onBindingFirebase() {
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseMyplant.add(snapshot.getValue(CateModel::class.java)!!)
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


