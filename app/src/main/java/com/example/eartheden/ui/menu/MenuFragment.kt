package com.example.eartheden.ui.menu

import android.annotation.SuppressLint
import android.content.ContentValues
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
import com.bumptech.glide.Glide
import com.example.eartheden.Category
import com.example.eartheden.Faq
import com.example.eartheden.Login
import com.example.eartheden.Profile
import com.example.eartheden.R
import com.example.eartheden.databinding.FragmentMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
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

        //user emall
//        //ดึงข้อมูล user
//        mAuth = FirebaseAuth.getInstance()
//        val myref = Firebase.database.reference
//        val user = mAuth!!.currentUser
//        val database = FirebaseDatabase.getInstance("https://eartheden-9818d-default-rtdb.asia-southeast1.firebasedatabase.app")
//        var menu_usertxt: TextView? = view?.findViewById<TextView>(R.id.menu_usertxt)
//        var menu_emailtxt: TextView? = view?.findViewById<TextView>(R.id.menu_emailtxt)
//
//        //name
//        val postListener1 = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // ข้อมูลที่ดึงมาอยู่ใน dataSnapshot
//                val value = dataSnapshot.getValue(String::class.java)
//                Log.d(TAG, "Value is: $value")
//                menu_usertxt?.text = value
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", databaseError.toException())
//            }
//        }
//
//        //ลบจุด
//        var tempMail1: String = user?.email.toString()
//        val mark1 = '.'
//        var newMail1 = ""
//        for (char in tempMail1) {
//            if (char != mark1) {
//                newMail1 += char
//            }
//        }
//
//        // ดึงข้อมูลแบบ Realtime
//        myref.child("Account")
//            .child(newMail1)
//            .child("User name").addValueEventListener(postListener1)
//
//        // สร้าง DatabaseReference ของ Firebase Realtime Database
//        val tempMail: String = user?.email.toString().replace(".", "") // ทำการลบจุดออก
//        val databaseReference = database.reference.child("Account").child(tempMail).child("Profile")
//
//        // เพิ่ม ValueEventListener เพื่อดึง URL ของรูปภาพจาก Realtime Database
////        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
////            override fun onDataChange(dataSnapshot: DataSnapshot) {
////                val imageUrl = dataSnapshot.getValue(String::class.java)
////                if (!imageUrl.isNullOrEmpty()) {
////                    // โหลดรูปภาพจาก URL ด้วย Glide หรือ Picasso หรือวิธีอื่นๆ
////                    Glide.with(requireContext())
////                        .load(imageUrl)
////                        .into(profile_button_homefeed!!)
////                } else {
////                    // ถ้าไม่มี URL ของรูปภาพ ให้ทำการกำหนดรูปภาพเริ่มต้นหรือตัวแทน
////                    profile_button_homefeed?.setImageResource(R.drawable.imgloading) // เปลี่ยน placeholder_image เป็นรูปภาพที่คุณต้องการให้แสดงเมื่อไม่มีรูปใน Realtime Database
////                }
////            }
////
////            override fun onCancelled(databaseError: DatabaseError) {
////                // กรณีเกิดข้อผิดพลาดในการอ่านค่าจาก Realtime Database
////                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
////            }
////        })
//
//        //email
//        val postListener2 = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // ข้อมูลที่ดึงมาอยู่ใน dataSnapshot
//                val value = dataSnapshot.getValue(String::class.java)
//                Log.d(TAG, "Value is: $user")
//                menu_emailtxt?.text = value
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", databaseError.toException())
//            }
//        }
//
//        //ลบจุด
//        var tempMail2: String = user?.email.toString()
//        val mark2 = '.'
//        var newMail2 = ""
//        for (char in tempMail2) {
//            if (char != mark2) {
//                newMail2 += char
//            }
//        }
//
//        // ดึงข้อมูลแบบ Realtime
//        myref.child("Account")
//            .child(newMail2)
//            .child("Email").addValueEventListener(postListener2)
//
//        val signoutButton = view?.findViewById<Button>(R.id.menu_logoutbtn)
//        signoutButton?.setOnClickListener {
//            mAuth.signOut()
//            Toast.makeText(requireContext(), "Signed out!", Toast.LENGTH_LONG).show()
//            Log.d(TAG, "Signed out!")
//            val intent = Intent(requireActivity(), Login::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }





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