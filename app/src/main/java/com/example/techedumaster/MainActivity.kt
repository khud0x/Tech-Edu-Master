package com.example.techedumaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.techedumaster.data.DeviceRepository
import com.example.techedumaster.screens.*
import com.example.techedumaster.ui.theme.TechEduMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TechEduMasterTheme {
                TechEduMasterApp()
            }
        }
    }
}

@Composable
fun TechEduMasterApp() {
    var screen by remember { mutableStateOf("home") }

    var selectedDeviceId by remember { mutableIntStateOf(1) }
    var finalScore by remember { mutableIntStateOf(0) }
    var studentName by remember { mutableStateOf("") }

    var passedDeviceTests by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val allDeviceTestsPassed =
        passedDeviceTests.size == DeviceRepository.devices.size

    when (screen) {

        "home" -> HomeScreen(
            onStartClick = { screen = "main_menu" },
            onAboutClick = { screen = "about" }
        )

        "about" -> AboutScreen(
            onBackClick = { screen = "home" }
        )

        "main_menu" -> MainMenuScreen(
            onPartsClick = { screen = "device_list" },
            onTestsClick = { screen = "tests_menu" },
            onSearchClick = { screen = "search" },
            onBackClick = { screen = "home" }
        )

        "device_list" -> DeviceListScreen(
            title = "Kompyuter qismlari",
            subtitle = "Qurilmani tanlang va ma’lumotlarini o‘rganing.",
            onDeviceClick = { deviceId ->
                selectedDeviceId = deviceId
                screen = "device_detail"
            },
            onBackClick = { screen = "main_menu" }
        )

        "device_detail" -> DeviceDetailScreen(
            deviceId = selectedDeviceId,
            onTestClick = { deviceId ->
                selectedDeviceId = deviceId
                screen = "mini_test"
            },
            onBackClick = { screen = "device_list" }
        )

        "mini_test" -> DeviceTestScreen(
            deviceId = selectedDeviceId,
            isMiniTest = true,
            onFinished = { _, _ -> },
            onBackClick = { screen = "device_detail" }
        )

        "tests_menu" -> TestsMenuScreen(
            onDeviceTestClick = { deviceId ->
                selectedDeviceId = deviceId
                screen = "device_test"
            },
            onFinalTestClick = {
                if (allDeviceTestsPassed) {
                    screen = "final_test"
                }
            },
            onBackClick = { screen = "main_menu" }
        )

        "device_test" -> DeviceTestScreen(
            deviceId = selectedDeviceId,
            isMiniTest = false,
            onFinished = { score, total ->
                val percent = if (total > 0) (score * 100) / total else 0

                if (percent >= 80) {
                    passedDeviceTests = passedDeviceTests + selectedDeviceId
                }
            },
            onBackClick = { screen = "tests_menu" }
        )

        "final_test" -> FinalTestScreen(
            onFinished = { score ->
                finalScore = score
                screen = "certificate_name"
            },
            onBackClick = { screen = "tests_menu" }
        )

        "certificate_name" -> CertificateNameScreen(
            onContinueClick = { name ->
                studentName = name
                screen = "certificate"
            },
            onBackClick = { screen = "tests_menu" }
        )

        "certificate" -> CertificateScreen(
            studentName = studentName,
            score = finalScore,
            onBackClick = { screen = "main_menu" }
        )

        "search" -> SearchScreen(
            onBackClick = { screen = "main_menu" }
        )
    }
}