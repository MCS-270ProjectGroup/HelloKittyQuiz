package com.example.hellokittyquiz

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ResultAdapter(var context: Context, var arraylist: ArrayList<Question>): BaseAdapter() {

    override fun getCount(): Int {
        return arraylist.size
    }

    override fun getItem(p0: Int): Any {
        return arraylist[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.question_item, null)
        var title: TextView = view.findViewById(R.id.question_title)
        var answerButton: Button = view.findViewById(R.id.user_answer)

        var resultList: Question = arraylist[p0]

        title.setText(resultList.textReId)
        if (resultList.userAnswer == null){
            answerButton.text = "UNANSWERED"
            answerButton.setBackgroundColor(Color.GRAY)
        }
        else if (resultList.userAnswer == true){
            answerButton.text = "TRUE"
            if(resultList.userAnswer == resultList.answer) answerButton.setBackgroundColor(Color.GREEN)
            else answerButton.setBackgroundColor(Color.RED)
        }
        else{
            answerButton.text = "FALSE"
            if(resultList.userAnswer == resultList.answer) answerButton.setBackgroundColor(Color.GREEN)
            else answerButton.setBackgroundColor(Color.RED)
        }

        return view!!
    }

}

