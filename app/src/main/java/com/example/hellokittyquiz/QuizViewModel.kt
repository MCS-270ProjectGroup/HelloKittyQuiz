package com.example.hellokittyquiz

import androidx.lifecycle.ViewModel


class QuizViewModel: ViewModel() {
    var currentIndex = 0
    var currentScore = 0

    private val questionBank = listOf(
        Question(R.string.kitty1, true),
        Question(R.string.kitty2, false),
        Question(R.string.kitty3, false),
        Question(R.string.kitty4, true)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textReId
    var currentStatus: Boolean
        get() = questionBank[currentIndex].isAnswered
        set(value){
            questionBank[currentIndex].isAnswered = value
        }
    var cheatStatus: Boolean
        get() = questionBank[currentIndex].isCheated
        set(value){
            questionBank[currentIndex].isCheated = value
        }


    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
    }

    fun allAnswered(): Boolean{
        for(question in questionBank){
            if (!question.isAnswered and !question.isCheated) return false
        }
        return true
    }


//    init {
//        Log.d(TAG, "View Model instance created")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d(TAG, "View Model instance destroyed")
//    }
}