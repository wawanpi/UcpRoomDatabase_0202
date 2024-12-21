package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.view.HomeView
import com.example.ucp2.ui.view.dosen.HomeDsnView
import com.example.ucp2.ui.view.dosen.InsertDsnView
import com.example.ucp2.ui.view.mataKuliah.DestinasiInsert
import com.example.ucp2.ui.view.mataKuliah.HomeMkView

@Composable
fun ControllerHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute.route, // Sesuaikan dengan HomeRoute
        modifier = modifier
    ) {
        // Rute untuk HomeView
        composable(route = HomeRoute.route) {
            HomeView(
                onDosenButton = {
                    navController.navigate(HomeDsnRoute.route) // Arahkan ke HomeDsnView
                },
                onMataKuliahButton = {
                    navController.navigate(HomeMkRoute.route) // Arahkan ke HomeMkView
                }
            )
        }

        // Rute untuk HomeDsnView
        composable(route = HomeDsnRoute.route) {
            HomeDsnView(
                onAddDsn = {
                    navController.navigate(DestinasiInsert.route)
                },
                onDetailClick = { nidn ->
                    // Tambahkan logika navigasi ke detail
                    println("Navigasi ke detail dengan NIDN: $nidn")
                },
                onBack = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiInsert.route
        )
        {
            InsertDsnView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }


        // Rute untuk HomeMkView
        composable(route = HomeMkRoute.route) {
            HomeMkView(
                onAddMk = {
                    navController.navigate(DestinasiInsert.route)
                },
                onDetailClick = { kode ->
                    // Tambahkan logika navigasi ke detail
                    println("Navigasi ke detail dengan Kode: $kode")
                },
                onBack = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiInsert.route
        )
        {
            InsertDsnView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
    }
}
