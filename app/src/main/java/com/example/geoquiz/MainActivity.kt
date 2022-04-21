package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView

    private val questionsBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_asia, true),
    )

    private var currentIndexQuestion = 0

    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.questions_text_view)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            isAnswered()
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            isAnswered()
        }

        val questionTextResId = questionsBank[currentIndexQuestion].textResId
        questionTextView.setText(questionTextResId)

        nextButton.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion + 1) % questionsBank.size
            updateQuestion()
            refreshButton()
        }

        prevButton.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion - 1) % questionsBank.size
            if (currentIndexQuestion < 0) currentIndexQuestion = 0
            updateQuestion()
            refreshButton()
        }

        questionTextView.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion + 1) % questionsBank.size
            updateQuestion()
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionsBank[currentIndexQuestion].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(answer: Boolean) {
        val correctAnswer = questionsBank[currentIndexQuestion].answer

        val messageResID = if (answer == correctAnswer) R.string.correct_toast
        else R.string.incorrect_toast

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
    }

    private fun isAnswered() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun refreshButton() {
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }
}

