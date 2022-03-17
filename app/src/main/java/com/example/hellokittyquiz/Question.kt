package com.example.hellokittyquiz

import java.io.Serializable


data class Question(val textReId: Int,
                    val answer: Boolean,
                    var isAnswered: Boolean = false,
                    var isCheated: Boolean = false,
                    var userAnswer: Boolean? = null): Serializable
