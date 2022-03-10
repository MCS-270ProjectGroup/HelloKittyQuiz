package com.example.hellokittyquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var questionTextView: TextView

    private lateinit var image_next: ImageButton
    private lateinit var image_prev: ImageButton

    private lateinit var cheatButton: Button


    private val TAG = "MainActivity"


    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel:: class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

//        val provider: ViewModelProvider = ViewModelProviders.of(this)
//        val QuizViewModel = provider.get(QuizViewModel::class.java)
//        Log.d(TAG, "We got a QuizViewModel")


        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        //nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        //previousButton = findViewById(R.id.previous_button)

        image_next = findViewById(R.id.image_right)
        image_prev = findViewById(R.id.image_left)

        cheatButton = findViewById(R.id.cheat_button)

        questionTextView.setOnClickListener {
            if (quizViewModel.allAnswered()){
                val grade = quizViewModel.currentScore.divideToPercent(4)
                Toast.makeText(this, "Final Grade: $grade%", Toast.LENGTH_LONG).show()
            }
            else {
                quizViewModel.moveToNext()
                updateQuestion()
            }
        }

        trueButton.setOnClickListener{
            //post response when click true button
            checkAnswer(true)
            trueButton.setEnabled(!(quizViewModel.currentStatus))
            falseButton.setEnabled(!(quizViewModel.currentStatus))
        }
        falseButton.setOnClickListener{
            //post response when click false button
            checkAnswer(false)
            trueButton.setEnabled(!(quizViewModel.currentStatus))
            falseButton.setEnabled(!(quizViewModel.currentStatus))
        }

//        nextButton.setOnClickListener {
//            //rotates question
//            currentIndex = (currentIndex + 1) % questionBank.size
//            updateQuestion()
//        }

        image_next.setOnClickListener{
            if (quizViewModel.allAnswered()){
                val grade = quizViewModel.currentScore.divideToPercent(4)
                Toast.makeText(this, "Final Grade: $grade%", Toast.LENGTH_LONG).show()
            }
            else {
                quizViewModel.moveToNext()
                updateQuestion()
            }
        }

        image_prev.setOnClickListener{
            if (quizViewModel.allAnswered()){
                val grade = quizViewModel.currentScore.divideToPercent(4)
                Toast.makeText(this, "Final Grade: $grade%", Toast.LENGTH_LONG).show()
            }
            else {
                quizViewModel.moveToPrev()
                updateQuestion()
            }
        }

        cheatButton.setOnClickListener {
            // start cheatActivity
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)

            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }

        updateQuestion()

    }// end of onCreate

    /********************** After onCreate() **********************************/
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() Called")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK){
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.cheatStatus =
                data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    /***************************Help Functions*******************************************/
    private fun Int.divideToPercent(divideTo: Int): Int{
        return if (divideTo == 0) 0
        else ((this / divideTo.toDouble())*100).toInt()
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

        trueButton.setEnabled(!(quizViewModel.currentStatus))
        falseButton.setEnabled(!(quizViewModel.currentStatus))

    }// end of updateQuestion

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId: Int

        when {
            quizViewModel.cheatStatus -> {
                messageResId = R.string.warning_text
            }
            userAnswer == correctAnswer -> {
                messageResId = R.string.correct_toast
                quizViewModel.currentScore += 1
                quizViewModel.currentStatus = true
            }
            else -> {
                messageResId = R.string.incorrect_toast
                quizViewModel.currentStatus = true
            }
        }
        val myToast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        myToast.setGravity(0, 0, -300)
        myToast.show()
    }// end checkAnswer


}// end of MainActivity