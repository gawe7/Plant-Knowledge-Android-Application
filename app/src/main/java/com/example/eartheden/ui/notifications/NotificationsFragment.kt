package com.example.eartheden.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.CateModel
import com.example.eartheden.Category
import com.example.eartheden.NotiModel
import com.example.eartheden.databinding.FragmentNotificationsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NotificationsFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var recyclerViewNoti: RecyclerView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReferenceCactus: DatabaseReference
    private lateinit var responseNoti: MutableList<NotiModel>
    private var adapter: NotiAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerViewNoti = binding.notiRecycleView
        recyclerViewNoti.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")
        databaseReferenceCactus = database.getReference("Notification")

        responseNoti = mutableListOf()
        adapter = NotiAdapter(responseNoti as ArrayList<NotiModel>)
        recyclerViewNoti.adapter = adapter

        onBindingFirebase()



        return view
    }


    private fun onBindingFirebase() {
        databaseReferenceCactus.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseNoti.add(snapshot.getValue(NotiModel::class.java)!!)
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