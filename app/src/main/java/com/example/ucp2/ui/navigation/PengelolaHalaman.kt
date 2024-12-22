package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.dokter.DestinasiInsertDokter
import com.example.ucp2.ui.view.dokter.HomeDokterView
import com.example.ucp2.ui.view.dokter.InsertDokterView
import com.example.ucp2.ui.view.jadwal.DestinasiInsertJadwal
import com.example.ucp2.ui.view.jadwal.DetailJadwalView
import com.example.ucp2.ui.view.jadwal.HomeJadwalView
import com.example.ucp2.ui.view.jadwal.InsertJadwalView
import com.example.ucp2.ui.view.jadwal.UpdateJadwalView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AlamatNavigasi.DestinasiHomeDokter.route
    ) {
        composable(route = AlamatNavigasi.DestinasiHomeDokter.route
        ){
            HomeDokterView(
                onJadwalView = {
                    navController.navigate(AlamatNavigasi.DestinasiHomeJadwal.route)

                },
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
        composable(route = AlamatNavigasi.DestinasiHomeJadwal.route
        ){
            HomeJadwalView(
                onDetailClick = {id ->
                    navController.navigate("${AlamatNavigasi.DestinasiDetailJadwal.route}/$id")
                    println("PengelolaHalaman: id = $id")
                },
                onAddJadwal = {
                    navController.navigate(DestinasiInsertJadwal.route)
                },
                onKembali = {
                    navController.navigate(AlamatNavigasi.DestinasiHomeDokter.route)
                },
                modifier = modifier
            )
        }
        composable(
            AlamatNavigasi.DestinasiDetailJadwal.routeWithArgs,
            arguments = listOf(
                navArgument(AlamatNavigasi.DestinasiDetailJadwal.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(AlamatNavigasi.DestinasiDetailJadwal.ID)
            id?.let { id ->
                DetailJadwalView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${AlamatNavigasi.DestinasiUpdateJadwal.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            AlamatNavigasi.DestinasiUpdateJadwal.routeWithArgs,
            arguments = listOf(
                navArgument(AlamatNavigasi.DestinasiUpdateJadwal.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}

