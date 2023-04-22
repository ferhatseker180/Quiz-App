package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Questions>? = null
    private var mSelectedOptionPosition : Int = 0

    private var buttonSubmit : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mQuestionsList = Constants.getQuestions()
        //   Log.e("Questions",questionsList.toString())
        setQuestion()


    }

    private fun setQuestion() {

        val question: Questions = mQuestionsList!![mCurrentPosition - 1]

        progressBar.progress = mCurrentPosition
        textViewProgress.text = "$mCurrentPosition / ${progressBar.max}"
        textViewQuestions.text = question.questions
        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionFour
        imageView.setImageResource(question.image)
        buttonSubmit = findViewById(R.id.buttonSubmit)
    }

    override fun onClick(v: View?) {

    }
}