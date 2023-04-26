package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart : Button = findViewById(R.id.buttonStart)
        val editTextStart : EditText = findViewById(R.id.editTextStart)

        buttonStart.setOnClickListener {

            if (editTextStart.text.isEmpty()) {
                Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this@MainActivity,QuizActivity::class.java)
                intent.putExtra(Constants.USER_NAME,editTextStart.text.toString())
                startActivity(intent)

            }

        }


    }
}