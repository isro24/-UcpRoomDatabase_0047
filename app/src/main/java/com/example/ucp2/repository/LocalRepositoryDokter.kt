package com.example.ucp2.repository

import com.example.ucp2.data.dao.DokterDAO
import com.example.ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDokter (
    private val dokterDao: DokterDAO
) : RepositoryDokter {
    override suspend fun insertDktr(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    override fun getAllDktr(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }
}