    package com.example.techedumaster.screens

    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Card
    import androidx.compose.material3.CardDefaults
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextButton
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp

    @Composable
    fun AboutScreen(
        onBackClick: () -> Unit
    ) {
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
                text = "Loyiha haqida",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF101B2D))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Tech Edu Master",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Tech Edu Master — kompyuter qurilmalarini rasm, ma’lumot, video va testlar orqali o‘rgatuvchi multimediali Android ilova.",
                        color = Color(0xFFCBD5E1),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Texnologiyalar:",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "• Kotlin\n• Jetpack Compose\n• Android Studio\n• Multimedia ta’lim yondashuvi",
                        color = Color(0xFFCBD5E1),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }