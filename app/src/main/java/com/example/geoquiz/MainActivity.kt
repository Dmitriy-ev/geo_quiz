package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val qustionsBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_asia, true),
    )

    private var currentIndexQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.questions_text_view)

        trueButton.setOnClickListener { view: View -> checkAnswer(true) }

        falseButton.setOnClickListener { view: View -> checkAnswer(false) }

        val questionTextResId = qustionsBank[currentIndexQuestion].textResId
        questionTextView.setText(questionTextResId)

        nextButton.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion + 1) % qustionsBank.size
            updateQuestion()
        }

        prevButton.setOnClickListener{
            currentIndexQuestion = (currentIndexQuestion - 1) % qustionsBank.size
            if(currentIndexQuestion < 0) currentIndexQuestion = 0
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            currentIndexQuestion = (currentIndexQuestion + 1) % qustionsBank.size
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = qustionsBank[currentIndexQuestion].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(answer: Boolean) {
        val correctAnswer = qustionsBank[currentIndexQuestion].answer
        val messageResID = if (answer == correctAnswer) R.string.correct_toast
        else R.string.incorrect_toast

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
    }
}

