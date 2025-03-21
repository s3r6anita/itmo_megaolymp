package com.example.mynewsapplication.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDate(input: String): String {
    // Парсим строку в Instant
    val instant = Instant.parse(input)

    // Преобразуем Instant в локальное время (например, в системной временной зоне)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    // Форматируем дату и время в нужный формат
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.getDefault())
    return localDateTime.format(formatter)
}