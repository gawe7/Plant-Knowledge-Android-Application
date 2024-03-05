package com.example.eartheden.ui.menu

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eartheden.Category
import com.example.eartheden.Faq
import com.example.eartheden.Login
import com.example.eartheden.Profile
import com.example.eartheden.R
import com.example.eartheden.databinding.FragmentMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


//setting
class MenuFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


//        val MenuViewModel =
//            ViewModelProvider(this).get(MenuViewModel::class.java)

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val menu_profilemore = root.findViewById<ImageButton>(R.id.menu_profilemore)
        menu_profilemore.setOnClickListener {
            val intent = Intent(requireContext(), Profile::class.java)
            startActivity(intent)
        }

        val menu_faqmore = root.findViewById<ImageButton>(R.id.menu_faqmore)
        menu_faqmore.setOnClickListener {
            val intent = Intent(requireContext(), Faq::class.java)
            startActivity(intent)
        }

        // Set onClickListener to the night mode switch
        val nightModeSwitch = root.findViewById<Switch>(R.id.night_mode_switch)
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Perform action when night mode is enabled
                binding.menuNightModeTxt.text = "Night Mode"
                // Add your night mode logic here
            } else {
                // Perform action when night mode is disabled
                binding.menuNightModeTxt.text = "Night Mode"
                // Add your day mode logic here
            }
        }

//        //show cuurent user
//        //ดึงข้อมูล user
//        var mAuth: FirebaseAuth? = null
//        mAuth = FirebaseAuth.getInstance()
//
//        val myref = Firebase.database.reference
//        val user = mAuth!!.currentUser
//        var profile_text_name:TextView? = requireView().findViewById<TextView>(R.id.menu_usertxt)
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // ข้อมูลที่ดึงมาอยู่ใน dataSnapshot
//                val value = dataSnapshot.getValue(String::class.java)
//                Log.d(TAG, "Value is: $value")
//                profile_text_name?.setText(value)
//            }
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", databaseError.toException())
//            }
//        }
//        //ลบจุด
//        var tempMail:String = user?.email.toString()
//        val mark = '.'
//        var newMail = ""
//        for (char in tempMail){
//            if(char != mark){
//                newMail += char
//            }
//        }
//        // ดึงข้อมูลแบบ Realtime
//        myref.child("Account")
//            .child(newMail)
//            .child("user").addValueEventListener(postListener)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}