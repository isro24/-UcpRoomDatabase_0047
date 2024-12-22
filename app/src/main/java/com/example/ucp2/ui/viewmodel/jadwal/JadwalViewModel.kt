package com.example.ucp2.ui.viewmodel.jadwal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.repository.RepositoryJadwal
import kotlinx.coroutines.launch

class JadwalViewModel(private val repositoryJadwal: RepositoryJadwal) : ViewModel() {
    var uistate by mutableStateOf(JadwalUIState())

    fun updateState(jadwalEvent: JadwalEvent) {
        uistate = uistate.copy(
            jadwalEvent = jadwalEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = uistate.jadwalEvent
        val errorState = FormErrorState(
            namaDokter = if (event.namaDokter.isNotEmpty()) null else "Nama Dokter tidak boleh kosong",
            namaPasien = if (event.namaPasien.isNotEmpty()) null else "Nama Pasien tidak boleh kosong",
            noHp = if (event.noHp.isNotEmpty()) null else "No HP tidak boleh kosong",
            tanggalKonsultasi = if (event.tanggalKonsultasi.isNotEmpty()) null else "Tanggal Konsultasi tidak boleh kosong",
            status = if (event.status.isNotEmpty()) null else "Status tidak boleh kosong"
        )

        uistate = uistate.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uistate.jadwalEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    println("Mencoba menyimpan data: $currentEvent")
                    repositoryJadwal.insertJdwl(currentEvent.toJadwalEntity())
                    println("Data berhasil disimpan")
                    uistate = uistate.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    println("Gagal menyimpan data: ${e.message}")
                    uistate = uistate.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            println("Validasi gagal: ${uistate.isEntryValid}")
            uistate = uistate.copy(
                snackBarMessage = "Input tidak valid, periksa kembali data Anda"
            )
        }
    }

    fun resetSnackBarMessage() {
        uistate = uistate.copy(snackBarMessage = null)
    }
}

data class JadwalUIState(
    val jadwalEvent: JadwalEvent = JadwalEvent(),
    val jadwalList: List<Jadwal> = emptyList(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class JadwalEvent(
    val id: String = "",
    val namaDokter: String = "",
    val namaPasien: String = "",
    val noHp: String = "",
    val tanggalKonsultasi: String = "",
    val status: String = ""
)

fun JadwalEvent.toJadwalEntity(): Jadwal = Jadwal(
    id = id,
    namaDokter = namaDokter,
    namaPasien = namaPasien,
    noHp = noHp,
    tanggalKonsultasi = tanggalKonsultasi,
    status = status
)

data class FormErrorState(
    val namaDokter: String? = null,
    val namaPasien: String? = null,
    val noHp: String? = null,
    val tanggalKonsultasi: String? = null,
    val status: String? = null,
) {
    fun isValid(): Boolean {
        return namaDokter == null
                && namaPasien == null
                && noHp == null
                && tanggalKonsultasi == null
                && status == null
    }
}