package com.example.techedumaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CertificateNameScreen(
    onContinueClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text("← Orqaga", color = Color(0xFF60A5FA))
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Sertifikat uchun ma’lumot",
            color = Color.White,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Ism va familiyangizni kiriting. Sertifikatda shu nom chiqadi.",
            color = Color(0xFFCBD5E1),
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Ism familiya") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (fullName.isNotBlank()) {
                    onContinueClick(fullName.trim())
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Sertifikatni ko‘rish")
        }
    }
}