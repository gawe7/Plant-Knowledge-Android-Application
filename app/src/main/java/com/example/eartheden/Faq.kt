package com.example.eartheden

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Faq : AppCompatActivity() {

    private var faq_back: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_faq)
        init()
        faq_back?.setOnClickListener {
            onBackPressed()
        }

    }
    private fun init() {
        faq_back = findViewById(R.id.faq_backbtn)
    }


    fun onQuestionClick(view: View) {
        val questionId = view.id
        val answerId = when (questionId) {
            R.id.que1 -> R.id.ans1
            R.id.que2 -> R.id.ans2
            R.id.que3 -> R.id.ans3
            R.id.que4 -> R.id.ans4
            else -> return
        }
        val answerTextView = findViewById<TextView>(answerId)
        if (answerTextView.visibility == View.VISIBLE) {
            answerTextView.visibility = View.GONE
        } else {
            // Hide all answer TextViews first
            hideAllAnswers()
            answerTextView.visibility = View.VISIBLE
        }
    }

    private fun hideAllAnswers() {
        val ans1 = findViewById<TextView>(R.id.ans1)
        val ans2 = findViewById<TextView>(R.id.ans2)
        val ans3 = findViewById<TextView>(R.id.ans3)
        val ans4 = findViewById<TextView>(R.id.ans4)
        ans1.visibility = View.GONE
        ans2.visibility = View.GONE
        ans3.visibility = View.GONE
        ans4.visibility = View.GONE
    }
}
