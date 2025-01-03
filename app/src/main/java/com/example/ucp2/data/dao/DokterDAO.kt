package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

@Dao
interface DokterDAO {
    @Insert
    suspend fun insertDokter(dokter: Dokter): Long

    @Query("SELECT * FROM dokter ORDER BY nama ASC")
    fun getAllDokter(): Flow<List<Dokter>>
}