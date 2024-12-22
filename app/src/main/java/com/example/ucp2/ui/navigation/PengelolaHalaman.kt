package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.ucp2.ui.view.dokter.DestinasiInsertDokter
import com.example.ucp2.ui.view.dokter.HomeDokterView
import com.example.ucp2.ui.view.dokter.InsertDokterView
import com.example.ucp2.ui.view.jadwal.DestinasiInsertJadwal
import com.example.ucp2.ui.view.jadwal.InsertJadwalView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiInsertJadwal.route
    ) {
        composable(route = AlamatNavigasi.DestinasiHomeDokter.route
        ){
            HomeDokterView(
                onDetailClick = {},
                onAddDokter = {
                    navController.navigate(DestinasiInsertDokter.route)
                },
                modifier = modifier
            )
        }
        composable(route = DestinasiInsertDokter.route) {
            InsertDokterView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.navigateUp()
                },
                modifier = modifier
            )
        }
        composable(route = DestinasiInsertJadwal.route) {
            InsertJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.navigateUp()
                },
                modifier = modifier
            )
        }
    }
}

