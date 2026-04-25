package com.example.techedumaster.data

import com.example.techedumaster.R

object DeviceRepository {

    val devices = listOf(
        Device(
            id = 1,
            name = "CPU",
            description = "CPU kompyuterning asosiy hisoblash qurilmasi. U buyruqlarni bajaradi va ma’lumotlarni qayta ishlaydi.",
            imageRes = R.drawable.cpu,
            parts = listOf("ALU", "Control Unit", "Cache Memory", "Registers"),
            videoUrl = ""
        ),

        Device(
            id = 2,
            name = "RAM",
            description = "RAM vaqtinchalik xotira hisoblanadi. Dasturlar ishlayotgan vaqtda kerakli ma’lumotlar shu yerda saqlanadi.",
            imageRes = R.drawable.ram,
            parts = listOf("Memory Cells", "Address Line", "Data Line", "Control Line"),
            videoUrl = ""
        ),

        Device(
            id = 3,
            name = "Motherboard",
            description = "Motherboard kompyuterning asosiy platasi bo‘lib, barcha qurilmalarni bir-biri bilan bog‘laydi.",
            imageRes = R.drawable.motherboard,
            parts = listOf("CPU Socket", "RAM Slot", "PCIe Slot", "SATA Port", "BIOS Chip"),
            videoUrl = ""
        ),

        Device(
            id = 4,
            name = "SSD",
            description = "SSD ma’lumotlarni tez saqlash va o‘qish uchun ishlatiladigan doimiy xotira qurilmasi.",
            imageRes = R.drawable.ssd,
            parts = listOf("NAND Flash", "Controller", "Connector", "Memory Chip"),
            videoUrl = ""
        ),

        Device(
            id = 5,
            name = "Monitor",
            description = "Monitor kompyuterdagi ma’lumotlarni ekranda ko‘rsatib beruvchi tashqi qurilma.",
            imageRes = R.drawable.monitor,
            parts = listOf("Display Panel", "HDMI Port", "Power Board", "Backlight"),
            videoUrl = ""
        ),

        Device(
            id = 6,
            name = "HDD",
            description = "HDD ma’lumotlarni magnit disklar orqali saqlaydigan doimiy xotira qurilmasi.",
            imageRes = R.drawable.hdd,
            parts = listOf("Disk Platter", "Read Write Head", "Motor", "Controller Board"),
            videoUrl = ""
        ),

        Device(
            id = 7,
            name = "GPU",
            description = "GPU grafik ma’lumotlarni qayta ishlaydi va tasvirlarni ekranga chiqarishda yordam beradi.",
            imageRes = R.drawable.gpu,
            parts = listOf("Graphics Processor", "VRAM", "Cooling Fan", "HDMI Port"),
            videoUrl = ""
        ),

        Device(
            id = 8,
            name = "Power Supply",
            description = "Power Supply kompyuter qismlariga elektr quvvatini taqsimlab beradi.",
            imageRes = R.drawable.power_supply,
            parts = listOf("Fan", "Power Cable Port", "Transformer", "Connectors"),
            videoUrl = ""
        ),

        Device(
            id = 9,
            name = "Keyboard",
            description = "Keyboard matn kiritish va buyruqlar berish uchun ishlatiladigan tashqi qurilma.",
            imageRes = R.drawable.keyboard,
            parts = listOf("Keys", "Switches", "USB Cable", "Controller"),
            videoUrl = ""
        ),

        Device(
            id = 10,
            name = "Mouse",
            description = "Mouse kursorni boshqarish, tanlash va buyruqlar berish uchun ishlatiladi.",
            imageRes = R.drawable.mouse,
            parts = listOf("Sensor", "Left Button", "Right Button", "Scroll Wheel"),
            videoUrl = ""
        ),

        Device(
            id = 11,
            name = "Printer",
            description = "Printer elektron hujjatlarni qog‘ozga chop etish uchun ishlatiladigan qurilma.",
            imageRes = R.drawable.printer,
            parts = listOf("Paper Tray", "Ink Cartridge", "Print Head", "Roller"),
            videoUrl = ""
        ),

        Device(
            id = 12,
            name = "Speaker",
            description = "Speaker kompyuterdagi audio signallarni ovoz ko‘rinishida chiqaradi.",
            imageRes = R.drawable.speaker,
            parts = listOf("Driver", "Cone", "Magnet", "Audio Cable"),
            videoUrl = ""
        )
    )

    fun getDeviceById(id: Int): Device? {
        return devices.find { it.id == id }
    }

    val miniQuestions = devices.flatMap { device ->
        listOf(
            Question(
                id = device.id * 100 + 1,
                deviceId = device.id,
                question = "${device.name} nima uchun kerak?",
                options = listOf(
                    "Asosiy vazifasini bajarish uchun",
                    "Faqat bezak uchun",
                    "Faqat internet uchun",
                    "Faqat ovoz uchun"
                ),
                correctAnswerIndex = 0
            ),
            Question(
                id = device.id * 100 + 2,
                deviceId = device.id,
                question = "${device.name} kompyuter qurilmasimi?",
                options = listOf(
                    "Ha",
                    "Yo‘q",
                    "Faqat dastur",
                    "Faqat fayl"
                ),
                correctAnswerIndex = 0
            ),
            Question(
                id = device.id * 100 + 3,
                deviceId = device.id,
                question = "${device.name} haqida o‘rganish nima uchun kerak?",
                options = listOf(
                    "Kompyuterni yaxshiroq tushunish uchun",
                    "Faqat o‘yin uchun",
                    "Kerak emas",
                    "Faqat rasm uchun"
                ),
                correctAnswerIndex = 0
            )
        )
    }

    val sectionQuestions = devices.flatMap { device ->
        listOf(
            Question(device.id * 1000 + 1, device.id, "${device.name} ning asosiy vazifasi nima?", listOf("Kompyuter ishida muhim rol bajaradi", "Faqat bezak", "Faqat qog‘oz", "Faqat internet"), 0),
            Question(device.id * 1000 + 2, device.id, "${device.name} qaysi tizimga tegishli?", listOf("Kompyuter qurilmalari tizimiga", "Oshxona jihozlariga", "Transportga", "Sport jihozlariga"), 0),
            Question(device.id * 1000 + 3, device.id, "${device.name} ishlamasa nima bo‘lishi mumkin?", listOf("Kompyuter ishlashida muammo bo‘ladi", "Hech narsa bo‘lmaydi", "Telefon zaryad oladi", "Printer tezlashadi"), 0),
            Question(device.id * 1000 + 4, device.id, "${device.name} haqida bilim nima beradi?", listOf("Texnik tushuncha beradi", "Faqat musiqa", "Faqat rasm", "Faqat video"), 0),
            Question(device.id * 1000 + 5, device.id, "${device.name} ni o‘rganish qaysi sohada foydali?", listOf("Kompyuter texnikasi", "Oshpazlik", "Sport", "Rassomchilik"), 0),
            Question(device.id * 1000 + 6, device.id, "${device.name} qurilmasi nosoz bo‘lsa nima qilish kerak?", listOf("Tekshirish yoki ustaga ko‘rsatish", "Suvga solish", "Tashlab yuborish", "Hech narsa qilmaslik"), 0),
            Question(device.id * 1000 + 7, device.id, "${device.name} kompyuter ichida yoki tashqarisida bo‘lishi mumkinmi?", listOf("Ha, turiga bog‘liq", "Yo‘q", "Faqat devorda", "Faqat internetda"), 0),
            Question(device.id * 1000 + 8, device.id, "${device.name} haqida ma’lumot qayerdan olinadi?", listOf("Darslik, video va ochiq manbalardan", "Faqat televizordan", "Faqat o‘yindan", "Faqat rasmdan"), 0),
            Question(device.id * 1000 + 9, device.id, "${device.name} ning qismlarini bilish nima uchun kerak?", listOf("Nosozlik va ishlash prinsipini tushunish uchun", "Faqat nomini yodlash uchun", "Faqat rangini bilish uchun", "Kerak emas"), 0),
            Question(device.id * 1000 + 10, device.id, "${device.name} bo‘yicha test nima uchun kerak?", listOf("Bilimni tekshirish uchun", "Kompyuterni o‘chirish uchun", "Internetni uzish uchun", "Rasm chizish uchun"), 0)
        )
    }

    val finalQuestions = listOf(
        Question(9001, 0, "Agar kompyuter yoqiladi, lekin ekranga tasvir chiqmasa, qaysi qurilmalarda muammo bo‘lishi mumkin?", listOf("Monitor, GPU yoki RAM", "Faqat klaviatura", "Faqat printer", "Faqat speaker"), 0),
        Question(9002, 0, "CPU, RAM va SSD birgalikda qanday ishlaydi?", listOf("CPU buyruqlarni bajaradi, RAM vaqtinchalik ma’lumot saqlaydi, SSD doimiy saqlaydi", "CPU faqat ovoz chiqaradi, RAM rasm chizadi, SSD internet beradi", "CPU monitorni yoritadi, RAM klaviaturani boshqaradi, SSD printerni ishlatadi", "Ularning barchasi faqat elektr kabel vazifasini bajaradi"), 0),
        Question(9003, 0, "Kompyuter sekin ishlayapti. Qaysi yechimlar foydali bo‘lishi mumkin?", listOf("RAMni oshirish, SSD o‘rnatish, ortiqcha dasturlarni kamaytirish", "Monitor kabelini almashtirish", "Klaviatura tugmalarini bo‘yash", "Speaker ovozini pasaytirish"), 0),
        Question(9004, 0, "Motherboard nima uchun barcha qurilmalar ishida muhim hisoblanadi?", listOf("U CPU, RAM, GPU, SSD va boshqa qismlarni bog‘laydi", "U faqat ovoz chiqaradi", "U faqat qog‘oz chop etadi", "U faqat internet sahifasini ochadi"), 0),
        Question(9005, 0, "Power Supply kuchsiz yoki nosoz bo‘lsa, qanday muammo yuzaga kelishi mumkin?", listOf("Kompyuter o‘chib qolishi yoki umuman yoqilmasligi mumkin", "Monitor rangi o‘zgaradi, lekin kompyuterga ta’sir qilmaydi", "Printer tezroq ishlaydi", "Klaviatura avtomatik tarjima qiladi"), 0),
        Question(9006, 0, "SSD va HDD orasidagi asosiy farq nima?", listOf("SSD tezroq ishlaydi va harakatlanuvchi mexanik qismi yo‘q, HDD esa magnit diskdan foydalanadi", "HDD har doim SSDdan tezroq bo‘ladi", "SSD faqat ovoz uchun, HDD faqat monitor uchun ishlatiladi", "SSD printerga ulanadi, HDD esa klaviaturaga ulanadi"), 0),
        Question(9007, 0, "Agar RAM yetarli bo‘lmasa, foydalanuvchi qanday holatni sezishi mumkin?", listOf("Dasturlar sekin ochiladi, ko‘p vazifa bajarganda kompyuter qotishi mumkin", "Monitor umuman elektr olmaydi", "Printer qog‘ozni tezroq chiqaradi", "Mouse batareyasi ko‘payadi"), 0),
        Question(9008, 0, "GPU qaysi holatda ayniqsa muhim bo‘ladi?", listOf("Grafika, video montaj, 3D va o‘yinlarda", "Faqat matn terishda", "Faqat sichqoncha harakatida", "Faqat printer sozlamasida"), 0),
        Question(9009, 0, "Kompyuter qurilmasini almashtirishdan oldin eng to‘g‘ri yondashuv qaysi?", listOf("Muammoni aniqlash, moslikni tekshirish, keyin almashtirish", "Darhol hamma qismlarni tashlab yuborish", "Faqat tashqi ko‘rinishga qarab tanlash", "Kompyuter yoqilgan holda barcha qismlarni sug‘urish"), 0),
        Question(9010, 0, "CPU Socket va RAM Slot nima bilan farqlanadi?", listOf("CPU Socket protsessor uchun, RAM Slot esa tezkor xotira moduli uchun", "Ikkalasi ham faqat monitor ulash uchun", "CPU Socket klaviatura uchun, RAM Slot printer uchun", "Ular kompyuterda umuman bo‘lmaydi"), 0)
    )

    fun getMiniQuestionsByDevice(deviceId: Int): List<Question> {
        return miniQuestions.filter { it.deviceId == deviceId }
    }

    fun getSectionQuestionsByDevice(deviceId: Int): List<Question> {
        return sectionQuestions.filter { it.deviceId == deviceId }
    }
}