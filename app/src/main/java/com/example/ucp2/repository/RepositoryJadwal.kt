package com.example.ucp2.repository

import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJadwal {
    suspend fun insertJdwl(jadwal: Jadwal)

    fun getAllJdwl(): Flow<List<Jadwal>>

    fun getJdwl (nim: String): Flow<Jadwal>

    suspend fun deleteJdwl(jadwal: Jadwal)

    suspend fun updateJdwl(jadwal: Jadwal)
}