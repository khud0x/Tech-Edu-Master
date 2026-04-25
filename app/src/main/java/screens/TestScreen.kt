package com.example.techedumaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techedumaster.data.DeviceRepository

@Composable
fun DeviceTestScreen(
    deviceId: Int,
    isMiniTest: Boolean,
    onFinished: ((score: Int, total: Int) -> Unit)? = null,
    onBackClick: () -> Unit
) {
    val device = DeviceRepository.getDeviceById(deviceId)

    val questions = if (isMiniTest) {
        DeviceRepository.getMiniQuestionsByDevice(deviceId)
    } else {
        DeviceRepository.getSectionQuestionsByDevice(deviceId)
    }

    var selectedAnswers by remember {
        mutableStateOf<Map<Int, Int>>(emptyMap())
    }

    var showResult by remember {
        mutableStateOf(false)
    }

    val score = questions.count { question ->
        selectedAnswers[question.id] == question.correctAnswerIndex
    }

    val percent = if (questions.isNotEmpty()) {
        (score * 100) / questions.size
    } else {
        0
    }

    val passed = if (isMiniTest) {
        percent >= 60
    } else {
        percent >= 80
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text("← Orqaga", color = Color(0xFF60A5FA))
        }

        Text(
            text = if (isMiniTest) {
                "${device?.name ?: "Qurilma"} mini testi"
            } else {
                "${device?.name ?: "Qurilma"} bo‘yicha test"
            },
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (isMiniTest) {
                "Qismlar ichidagi 3 ta mini test"
            } else {
                "Bu testdan kamida 80% olsangiz sertifikat testi ochiladi"
            },
            color = Color(0xFFCBD5E1),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        questions.forEachIndexed { index, question ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF101B2D)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${index + 1}. ${question.question}",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    question.options.forEachIndexed { optionIndex, option ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            RadioButton(
                                selected = selectedAnswers[question.id] == optionIndex,
                                onClick = {
                                    if (!showResult) {
                                        selectedAnswers = selectedAnswers.toMutableMap().apply {
                                            put(question.id, optionIndex)
                                        }
                                    }
                                }
                            )

                            Text(
                                text = option,
                                color = Color(0xFFCBD5E1),
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                showResult = true
                onFinished?.invoke(score, questions.size)
            },
            enabled = selectedAnswers.size == questions.size,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Natijani ko‘rish")
        }

        if (showResult) {
            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (passed) {
                        Color(0xFF123B2A)
                    } else {
                        Color(0xFF3B1D1D)
                    }
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Natija: $score / ${questions.size}",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Foiz: $percent%",
                        color = Color.White,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (passed) {
                            "Muvaffaqiyatli topshirildi ✅"
                        } else {
                            if (isMiniTest) {
                                "Qayta urinib ko‘ring ❌"
                            } else {
                                "Sertifikat testi uchun kamida 80% kerak ❌"
                            }
                        },
                        color = if (passed) Color(0xFF22C55E) else Color(0xFFF87171),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}