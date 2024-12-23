package com.example.ucp2.data.model

import androidx.compose.ui.graphics.Color

fun DokterSpesialisColor (spesialis: String): Color {
    return when (spesialis){
        "Dokter Mata" -> Color.Blue
        "Dokter Gigi" -> Color.Green
        "Dokter Umum" -> Color.Red
        "Dokter Anak" -> Color.Magenta
        "Dokter Kulit" -> Color.Cyan
        else -> Color.Gray
    }
}