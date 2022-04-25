package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.Gravity
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
    private lateinit var resultButton: Button
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
    private var answeredQuestionCount = 0
    private var correctAnsweredQuestionCount = 0
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
        resultButton = findViewById(R.id.result_button)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        val questionTextResId = questionsBank[currentIndexQuestion].textResId
        questionTextView.setText(questionTextResId)

        nextButton.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion + 1) % questionsBank.size
            isAnswered(currentIndexQuestion)
            updateQuestion()
        }

        prevButton.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion - 1) % questionsBank.size
            if (currentIndexQuestion < 0) currentIndexQuestion = 0
            isAnswered(currentIndexQuestion)
            updateQuestion()
        }

        resultButton.setOnClickListener {
            showResult()
        }

        questionTextView.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion + 1) % questionsBank.size
            updateQuestion()
        }
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

        val messageResID = if (answer == correctAnswer) {
            correctAnsweredQuestionCount++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        questionsBank[currentIndexQuestion].answered = true
        answeredQuestionCount++
    }

    private fun isAnswered(index: Int) {
        val isQuestionAnswered = questionsBank[index].answered
        trueButton.isEnabled = !isQuestionAnswered
        falseButton.isEnabled = !isQuestionAnswered
    }

    private fun showResult() {
        val result = String.format(
            "%.2f",
            (correctAnsweredQuestionCount / answeredQuestionCount.toDouble()) * 100
        )
        Toast.makeText(
            this,
            "$result %",
            Toast.LENGTH_LONG
        ).apply { setGravity(Gravity.TOP, 0, 0) }.show()
    }
}

