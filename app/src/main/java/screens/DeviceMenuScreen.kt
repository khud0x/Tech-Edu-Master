package com.example.techedumaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeviceMenuScreen(
    deviceId: Int,
    deviceName: String,
    onPartsClick: () -> Unit,
    onTestClick: () -> Unit,
    onVideoClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text("← Orqaga", color = Color.White)
        }

        Text(
            text = deviceName,
            color = Color.White,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onPartsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Qismlar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onTestClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Test")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onVideoClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Video")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onSearchClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Internetdan izlash 🔍")
        }
    }
}