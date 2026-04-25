package com.example.techedumaster.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techedumaster.data.DeviceRepository

@Composable
fun VideoScreen(
    deviceId: Int,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val device = DeviceRepository.getDeviceById(deviceId)

    if (device == null) {
        Text("Video topilmadi")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text("← Orqaga")
        }

        Text(
            text = "${device.name} video darsi",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF101B2D))
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Image(
                    painter = painterResource(id = device.imageRes),
                    contentDescription = device.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Ushbu video orqali ${device.name} qurilmasining vazifasi va ishlash prinsipini o‘rganasiz.",
                    color = Color(0xFFCBD5E1),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(device.videoUrl))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("YouTube’da ochish")
                }
            }
        }
    }
}