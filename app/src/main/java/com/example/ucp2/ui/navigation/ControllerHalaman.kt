package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.HomeView
import com.example.ucp2.ui.view.dosen.DestinasiInsert
import com.example.ucp2.ui.view.dosen.HomeDsnView
import com.example.ucp2.ui.view.dosen.InsertDsnView
import com.example.ucp2.ui.view.mataKuliah.DestinasiInsertMk
import com.example.ucp2.ui.view.mataKuliah.DetailMkView
import com.example.ucp2.ui.view.mataKuliah.HomeMkView
import com.example.ucp2.ui.view.mataKuliah.InsertMkView
import com.example.ucp2.ui.view.mataKuliah.UpdateMkView
import com.example.ucp2.ui.viewmodel.DetailMkViewModel

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
                    navController.navigate(DestinasiInsertMk.route)
                },
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetail.route}/$kode")
                    println("PengelolaHalaman: kode =$kode")
                },
                onBack = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiInsertMk.route
        )
        {
            InsertMkView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetail.KODE)
            kode?.let { kode ->
                DetailMkView(
                    onBack = { navController.popBackStack() },
                    onEditClick = { navController.navigate( "${DestinasiUpdate.route}/$it") },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdate.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMkView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
    }
}



