package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val textViewName : TextView = findViewById(R.id.textViewUserName)
        val textViewScore : TextView = findViewById(R.id.textViewScore)
        val buttonFinish : TextView = findViewById(R.id.buttonFinish)

        textViewName.text = intent.getStringExtra(Constants.USER_NAME)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        textViewScore.text = "Your Score Is $correctAnswers out of $totalQuestions"

        buttonFinish.setOnClickListener {
            val intent = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}