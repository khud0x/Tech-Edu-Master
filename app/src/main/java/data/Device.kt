package com.example.techedumaster.data

data class Device(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val parts: List<String>,
    val videoUrl: String
)