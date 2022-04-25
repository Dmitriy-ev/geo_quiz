package com.example.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {


    private val questionsBank = mutableListOf(
        Question(R.string.question_australia, true,),
        Question(R.string.question_oceans, true,),
        Question(R.string.question_mideast, false,),
        Question(R.string.question_americas, true,),
        Question(R.string.question_africa, false,),
        Question(R.string.question_asia, true,),
    )

    var currentIndexQuestion = 0

    val currentQuestionAnswer: Boolean
        get() = questionsBank[currentIndexQuestion].answer

    val currentQuestionText: Int
        get() = questionsBank[currentIndexQuestion].textResId

    fun moveToNext() {
        currentIndexQuestion = (currentIndexQuestion + 1) % questionsBank.size
    }

    fun moveToPrev() {
        currentIndexQuestion = (currentIndexQuestion - 1) % questionsBank.size
        if (currentIndexQuestion < 0) currentIndexQuestion = 0
    }

    fun rememberAnswer(reply: Boolean){
        questionsBank[currentIndexQuestion].answered = reply
    }

    fun checkAnswer(): Boolean{
        return questionsBank[currentIndexQuestion].answered
    }
}