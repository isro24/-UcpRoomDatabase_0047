package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String

    object DestinasiHomeDokter : AlamatNavigasi {
        override val route = "home"
    }
    object DestinasiHomeJadwal : AlamatNavigasi {
        override val route = "homejadwal"
    }
}