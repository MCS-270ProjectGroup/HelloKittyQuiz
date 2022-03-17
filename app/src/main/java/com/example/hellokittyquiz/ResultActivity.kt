package com.example.hellokittyquiz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView


class ResultActivity : AppCompatActivity(){

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel:: class.java)
    }

    private var listView: ListView? = null
    private var resultAdapters: ResultAdapter? = null
    private var arrayList: ArrayList<Question>? = null

    private val TAG = "resultActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        listView = findViewById(R.id.list)
        arrayList = intent.getSerializableExtra("Question_Bank") as ArrayList<Question>?
        val testList = ArrayList(quizViewModel.questionBank)
        // testList.add(Question(8888, false))
        //Log.d(TAG, arrayList.toString())
        resultAdapters = ResultAdapter(applicationContext, arrayList!!)
        listView?.adapter = resultAdapters

    }


}