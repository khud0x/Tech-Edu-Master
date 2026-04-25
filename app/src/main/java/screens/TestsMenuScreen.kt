package com.example.techedumaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techedumaster.data.DeviceRepository

@Composable
fun TestsMenuScreen(
    onDeviceTestClick: (Int) -> Unit,
    onFinalTestClick: () -> Unit,
    onBackClick: () -> Unit
) {
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
            text = "Testlar",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Bu bo‘limdagi testlar Qismlar ichidagi 3 ta mini testdan alohida. Har bir qurilma bo‘yicha 10 tadan test mavjud.",
            color = Color(0xFFCBD5E1),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(DeviceRepository.devices) { device ->
                Card(
                    onClick = { onDeviceTestClick(device.id) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF101B2D))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = device.name,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "10 ta savol",
                            color = Color(0xFFCBD5E1),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onFinalTestClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text("Sertifikat uchun umumiy test")
                }
            }
        }
    }
}