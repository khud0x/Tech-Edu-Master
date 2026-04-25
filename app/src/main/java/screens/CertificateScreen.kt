package com.example.techedumaster.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techedumaster.R
import com.example.techedumaster.data.DeviceRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CertificateScreen(
    studentName: String,
    score: Int,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val total = DeviceRepository.finalQuestions.size
    val percent = if (total > 0) (score * 100) / total else 0
    val passed = percent >= 70
    val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())

    val pdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/pdf")
    ) { uri ->
        if (uri != null) {
            createCertificatePdf(
                context = context,
                uri = uri,
                studentName = studentName,
                percent = percent,
                date = date,
                passed = passed
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07111F))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("← Asosiy menyu", color = Color(0xFF60A5FA))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF101B2D)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFF10233F),
                                Color(0xFF111827)
                            )
                        )
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (passed) "🎉 Sertifikat" else "Natija",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = studentName,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (passed) {
                        "Tech Edu Master kursidagi “Kompyuter qurilmalari” o‘quv dasturini muvaffaqiyatli yakunladi."
                    } else {
                        "Sertifikat olish uchun kamida 70% natija kerak."
                    },
                    color = Color(0xFFCBD5E1),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text("Yo‘nalish: Kompyuter qurilmalari", color = Color.White, fontSize = 18.sp)
                Text("Natija: $score / $total", color = Color.White, fontSize = 18.sp)
                Text("Foiz: $percent%", color = Color.White, fontSize = 18.sp)
                Text("Sana: $date", color = Color.White, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(24.dp))

                if (passed) {
                    Button(
                        onClick = {
                            pdfLauncher.launch("Tech_Edu_Master_Certificate.pdf")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("PDF yuklab olish")
                    }
                } else {
                    Text(
                        text = "Qayta urinib ko‘ring",
                        color = Color(0xFFF87171),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

fun createCertificatePdf(
    context: Context,
    uri: Uri,
    studentName: String,
    percent: Int,
    date: String,
    passed: Boolean
) {
    val pdfDocument = PdfDocument()

    val pageWidth = 842
    val pageHeight = 595

    val pageInfo = PdfDocument.PageInfo.Builder(
        pageWidth,
        pageHeight,
        1
    ).create()

    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas

    val backgroundBitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.certificate_bg
    )

    val scaledBitmap = Bitmap.createScaledBitmap(
        backgroundBitmap,
        pageWidth,
        pageHeight,
        true
    )

    canvas.drawBitmap(scaledBitmap, 0f, 0f, null)

    val paint = Paint().apply {
        isAntiAlias = true
        color = android.graphics.Color.rgb(10, 31, 68)
        textAlign = Paint.Align.CENTER
    }

    if (passed) {
        paint.textSize = 42f
        paint.isFakeBoldText = true
        canvas.drawText(studentName, 421f, 300f, paint)

        paint.textSize = 20f
        paint.isFakeBoldText = true
        canvas.drawText("$percent%", 421f, 432f, paint)

        paint.textSize = 18f
        paint.isFakeBoldText = true
        canvas.drawText(date, 620f, 432f, paint)
    } else {
        paint.textSize = 36f
        paint.isFakeBoldText = true
        canvas.drawText("Sertifikat berilmadi", 421f, 300f, paint)

        paint.textSize = 22f
        paint.isFakeBoldText = false
        canvas.drawText("Natija: $percent%", 421f, 350f, paint)
    }

    pdfDocument.finishPage(page)

    context.contentResolver.openOutputStream(uri)?.use { outputStream ->
        pdfDocument.writeTo(outputStream)
    }

    pdfDocument.close()
}