package com.example.mydatasiswa.uicontroller.route

import com.example.mydatasiswa.R
import com.example.mydatasiswa.uicontroller.DestinasiNavigasi

object DestinasiEdit : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa // Anda mungkin perlu menambahkan string resource ini
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}