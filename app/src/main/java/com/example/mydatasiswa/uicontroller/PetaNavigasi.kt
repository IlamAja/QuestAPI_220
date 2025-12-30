package com.example.mydatasiswa.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mydatasiswa.uicontroller.route.DestinasiDetail
import com.example.mydatasiswa.uicontroller.route.DestinasiEdit
import com.example.mydatasiswa.uicontroller.route.DestinasiEntry
import com.example.mydatasiswa.uicontroller.route.DestinasiHome
import com.example.mydatasiswa.view.DetailScreen
import com.example.mydatasiswa.view.EditScreen
import com.example.mydatasiswa.view.EntrySiswaScreen
import com.example.mydatasiswa.view.HomeScreen

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = { itemId ->
                    navController.navigate("${DestinasiDetail.route}/$itemId")
                }
            )
        }

        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = DestinasiDetail.arguments
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DestinasiDetail.siswaIdArg)
            itemId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = { navController.navigate("${DestinasiEdit.route}/$it") }
                )
            }
        }

        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}