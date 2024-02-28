package com.example.eartheden.ui.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eartheden.Category
import com.example.eartheden.Login
import com.example.eartheden.Profile
import com.example.eartheden.R
import com.example.eartheden.databinding.FragmentMenuBinding
import com.example.eartheden.databinding.FragmentNotificationsBinding
import com.example.eartheden.ui.notifications.NotificationsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

//setting
class MenuFragment : Fragment() {



    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        // Set onClickListener to the logout button

//        val MenuViewModel =
//            ViewModelProvider(this).get(MenuViewModel::class.java)

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val menu_profilemore = root.findViewById<ImageButton>(R.id.menu_profilemore)
        menu_profilemore.setOnClickListener {
            val intent = Intent(requireContext(), Profile::class.java)
            startActivity(intent)
        }

//        val menu_faqmore = root.findViewById<ImageButton>(R.id.menu_faqmore)
//        menu_faqmore.setOnClickListener {
//            val intent = Intent(requireContext(), Faq::class.java)
//            startActivity(intent)
//        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}