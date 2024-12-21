package com.example.ucp2.repository

import com.example.ucp2.data.dao.JadwalDAO
import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepositoryJadwal (
    private val jadwalDAO: JadwalDAO
) : RepositoryJadwal{
    override suspend fun insertJdwl(jadwal: Jadwal) {
        jadwalDAO.insertJadwal(jadwal)
    }

    override fun getAllJdwl(): Flow<List<Jadwal>> {
        return jadwalDAO.getAllJadwal()
    }

    override fun getJdwl(id: String): Flow<Jadwal> {
        return jadwalDAO.getJadwal(id)
    }

    override suspend fun deleteJdwl(jadwal: Jadwal) {
        jadwalDAO.deleteJadwal(jadwal)
    }

    override suspend fun updateJdwl(jadwal: Jadwal) {
        jadwalDAO.updateJadwal(jadwal)
    }
}