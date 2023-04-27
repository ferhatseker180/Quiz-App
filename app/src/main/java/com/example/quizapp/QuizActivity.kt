package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Questions>? = null
    private var mSelectedOptionPosition : Int = 0

    private var mUsername : String? = null
    private var mCorrectAnswers : Int = 0

    private var buttonSubmit : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mUsername = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()
        //   Log.e("Questions",questionsList.toString())
        setQuestion()



    }

    private fun setQuestion() {

        defaultOptionsView()
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

        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        buttonSubmit?.setOnClickListener(this)


        if (mCurrentPosition == mQuestionsList!!.size) {
            buttonSubmit?.text = "FINISH"
        }else {
            buttonSubmit?.text = "SUBMIT"
        }

    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()

        textViewOptionOne.let {
            options.add(0,it)
        }

        textViewOptionTwo.let {
            options.add(1,it)
        }

        textViewOptionThree.let {
            options.add(2,it)
        }

        textViewOptionFour.let {
            options.add(3,it)
        }

        for (option in options) {

            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg

            )


        }

    }

    private fun selectedOptionView(textView: TextView, selectedOptionNum : Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface,Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            //  R.drawable.default_option_border_bg
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {

        when(view?.id){
            R.id.textViewOptionOne -> {
                textViewOptionOne.let {
                    selectedOptionView(it,1)
                }
            }

            R.id.textViewOptionTwo -> {
                textViewOptionTwo.let {
                    selectedOptionView(it,2)
                }
            }

            R.id.textViewOptionThree -> {
                textViewOptionThree.let {
                    selectedOptionView(it,3)
                }
            }

            R.id.textViewOptionFour -> {
                textViewOptionFour.let {
                    selectedOptionView(it,4)
                }
            }

            R.id.buttonSubmit -> {
                // Send
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {

                            val resultIntent = Intent(this@QuizActivity,ResultActivity::class.java)
                            resultIntent.putExtra(Constants.USER_NAME,mUsername)
                            resultIntent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            resultIntent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList?.size)
                            startActivity(resultIntent)
                            finish()
                        }

                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswers != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswers,R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        buttonSubmit?.text = "FINISH"
                    } else {
                        buttonSubmit?.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer : Int, drawableView : Int) {

        when(answer) {
            1 -> {
                textViewOptionOne.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2 -> {
                textViewOptionTwo.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            3 -> {
                textViewOptionThree.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            4 -> {
                textViewOptionFour.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}