package com.example.ucp2.data.entity

import androidx.room.Entity

@Entity(tableName = "jadwal")
data class Jadwal(
    val id: String,
    val namaDokter: String,
    val namaPasien: String,
    val noHp: String,
    val tanggalKonsultasi: String,
    val status: String,
)