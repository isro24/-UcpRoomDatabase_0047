package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String

    object DestinasiHomeDokter : AlamatNavigasi {
        override val route = "home"
    }
    object DestinasiHomeJadwal : AlamatNavigasi {
        override val route = "homejadwal"
    }
    object DestinasiDetailJadwal : AlamatNavigasi {
        override val route = "detail"
        const val ID = "id"
        val routeWithArgs = "$route/{$ID}"
    }
    object DestinasiUpdateJadwal : AlamatNavigasi {
        override val route = "update"
        const val ID = "id"
        val routeWithArgs = "$route/{$ID}"
    }
}