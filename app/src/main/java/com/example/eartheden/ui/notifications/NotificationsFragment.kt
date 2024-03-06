package com.example.eartheden.ui.notifications

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
import com.example.eartheden.databinding.FragmentNotificationsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// MY plant
class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val view = binding.root



        /*val cate = binding.homeCategory
        cate.setOnClickListener {
            val intent = Intent(requireContext(), Category::class.java)
            startActivity(intent)
            requireActivity().finish()
        }*/

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}


