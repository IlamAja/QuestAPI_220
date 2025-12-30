package com.example.mydatasiswa.uicontroller.route

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface DestinasiNavigasi {
    val route: String
    val titleRes: Int
}

object DestinasiDetail : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = 0 // Tidak ada judul khusus untuk AppBar
    const val siswaIdArg = "siswaId"
    val routeWithArgs = "$route/{$siswaIdArg}"
    val arguments = listOf(
        navArgument(siswaIdArg) {
            type = NavType.IntType
        }
    )
}