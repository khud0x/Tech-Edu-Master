package com.example.techedumaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techedumaster.data.DeviceRepository

@Composable
fun FinalTestScreen(
    onFinished: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val questions = DeviceRepository.finalQuestions

    var currentIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }

    if (questions.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF07111F))
                .padding(16.dp)
        ) {
            TextButton(onClick = onBackClick) {
                Text("← Orqaga", color = Color(0xFF60A5FA))
            }

            Text(
                text = "Savollar topilmadi",
                color = Color.White,
                fontSize = 24.sp
            )
        }
        return
    }

    val question = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text("← Orqaga", color = Color(0xFF60A5FA))
        }

        Text(
            text = "Sertifikat testi",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Savol ${currentIndex + 1} / ${questions.size}",
            color = Color(0xFFCBD5E1),
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF101B2D))
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = question.question,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(18.dp))

                question.options.forEachIndexed { index, option ->
                    Button(
                        onClick = {
                            val newScore = if (index == question.correctAnswerIndex) {
                                score + 1
                            } else {
                                score
                            }

                            if (currentIndex < questions.size - 1) {
                                score = newScore
                                currentIndex++
                            } else {
                                onFinished(newScore)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(option)
                    }
                }
            }
        }
    }
}