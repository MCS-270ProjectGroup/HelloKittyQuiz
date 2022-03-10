package com.example.hellokittyquiz

data class Question(val textReId: Int,
                    val answer: Boolean,
                    var isAnswered: Boolean = false,
                    var isCheated: Boolean = false)