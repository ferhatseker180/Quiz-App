package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionsList = Constants.getQuestions()
     //   Log.e("Questions",questionsList.toString())

        for (s in questionsList) {
            Log.e("Question",s.questions)
        }

        var currentPosition = 0
        val question : Questions = questionsList[currentPosition]

        progressBar.progress = currentPosition
        textViewProgress.text = "$currentPosition / ${progressBar.max}"


    }
}