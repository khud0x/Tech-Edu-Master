package com.example.techedumaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuScreen(
    onPartsClick: () -> Unit,
    onTestsClick: () -> Unit,
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
            Text("← Orqaga", color = Color(0xFF60A5FA))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tech Edu Master",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Kompyuter qismlarini multimedia orqali o‘rganing.",
            color = Color(0xFFCBD5E1),
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        MenuCard("Qismlar", "Rasm, ma’lumot, video va 3 ta test", "⚙️", onPartsClick)
        Spacer(modifier = Modifier.height(16.dp))
        MenuCard("Testlar", "Har bir qism testi va yakuniy sertifikat testi", "🧠", onTestsClick)
        Spacer(modifier = Modifier.height(16.dp))
        MenuCard("Internetdan izlash", "Ochiq manbadan qurilma haqida izlash", "🔍", onSearchClick)
    }
}

@Composable
fun MenuCard(
    title: String,
    subtitle: String,
    icon: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101B2D)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF10233F), Color(0xFF111827))
                    )
                )
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = icon, fontSize = 38.sp, modifier = Modifier.width(64.dp))

            Column {
                Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(subtitle, color = Color(0xFFCBD5E1), fontSize = 14.sp)
            }
        }
    }
}