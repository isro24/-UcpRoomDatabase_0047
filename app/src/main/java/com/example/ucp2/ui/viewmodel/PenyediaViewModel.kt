package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KlinikApp
import com.example.ucp2.ui.viewmodel.dokter.DokterViewModel
import com.example.ucp2.ui.viewmodel.dokter.HomeDokterViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            DokterViewModel(
                klinikApp().containerApp.repositoryDokter
            )
        }
        initializer {
            HomeDokterViewModel(
                klinikApp().containerApp.repositoryDokter
            )
        }
    }
}

fun CreationExtras.klinikApp(): KlinikApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KlinikApp)

