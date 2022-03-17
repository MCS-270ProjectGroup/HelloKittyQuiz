package com.example.hellokittyquiz

data class Result(val textReId: Int,
                  var isAnswered: Boolean = false,
                  var isCorrected: Boolean = false)