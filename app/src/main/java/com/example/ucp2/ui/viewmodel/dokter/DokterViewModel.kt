package com.example.ucp2.ui.viewmodel.dokter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.repository.RepositoryDokter
import kotlinx.coroutines.launch

class DokterViewModel (private val repositoryDokter: RepositoryDokter) : ViewModel(){
    var uistate by mutableStateOf(DokterUIState())

    fun updateState(dokterEvent: DokterEvent) {
        uistate = uistate.copy(
            dokterEvent = dokterEvent
        )
    }
    fun validateFields(): Boolean {
        val event = uistate.dokterEvent
        val errorState = FormErrorState(
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            spesialis = if (event.spesialis.isNotEmpty()) null else "Spesialis tidak boleh kosong",
            klinik = if (event.klinik.isNotEmpty()) null else "Klinik tidak boleh kosong",
            noHp = if (event.noHp.isNotEmpty()) null else "No HP tidak boleh kosong",
            jamKerja = if (event.jamKerja.isNotEmpty()) null else "Jam Kerja tidak boleh kosong"
        )

        uistate = uistate.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun saveData() {
        val currentEvent = uistate.dokterEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDokter.insertDktr(currentEvent.toDokterEntity())
                    uistate = uistate.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dokterEvent = DokterEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uistate = uistate.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uistate = uistate.copy(
                snackBarMessage = "Input tidak valid, Periksa kembali data Anda"
            )
        }
    }

    fun resetSnackBarMessage() {
        uistate = uistate.copy(snackBarMessage = null)
    }
}

data class DokterUIState(
    val dokterEvent: DokterEvent = DokterEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class DokterEvent(
    val id: Int  = 0,
    val nama: String = "",
    val spesialis: String = "",
    val klinik: String = "",
    val noHp: String = "",
    val jamKerja: String = ""
)

fun DokterEvent.toDokterEntity(): Dokter = Dokter(
    id = id,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    noHp = noHp,
    jamKerja = jamKerja
)

data class FormErrorState(
    val nama: String? = null,
    val spesialis: String? = null,
    val klinik: String? = null,
    val noHp: String? = null,
    val jamKerja: String? = null,
) {
    fun isValid(): Boolean {
        return nama == null
                && spesialis == null
                && klinik == null
                && noHp == null
                && jamKerja == null
    }
}