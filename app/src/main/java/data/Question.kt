package com.example.techedumaster.data

data class Question(
    val id: Int,
    val deviceId: Int,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)