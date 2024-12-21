package com.example.ucp2.repository

import com.example.ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDokter {
    suspend fun insertDktr(dokter: Dokter)

    fun getAllDktr(): Flow<List<Dokter>>
}