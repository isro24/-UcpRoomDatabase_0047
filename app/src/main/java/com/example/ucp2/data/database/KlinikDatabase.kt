package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DokterDAO
import com.example.ucp2.data.dao.JadwalDAO
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal

@Database(entities = [Dokter::class, Jadwal::class], version = 1, exportSchema = false)
abstract class KlinikDatabase : RoomDatabase() {
    abstract fun dokterDao(): DokterDAO
    abstract fun jadwalDao(): JadwalDAO

    companion object{
        @Volatile
        private var INSTANCE: KlinikDatabase? = null

        fun getDatabase(context: Context): KlinikDatabase{
            return (INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    KlinikDatabase::class.java,
                    "KlinikDatabase"
                )
                    .build().also { INSTANCE = it  }
            })
        }
    }

}