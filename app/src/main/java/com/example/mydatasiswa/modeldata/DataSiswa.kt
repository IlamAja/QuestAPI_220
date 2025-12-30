package com.example.mydatasiswa.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class DataSiswa(
    // Ubah id menjadi String agar cocok dengan response JSON dari PHP
    val id: String,
    val nama: String,
    val alamat: String,
    val telpon: String
)

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

fun DetailSiswa.toDataSiswa(): DataSiswa = DataSiswa(
    // Konversi id dari Int ke String saat mengirim data
    id = id.toString(),
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

fun DataSiswa.toUIStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun DataSiswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    // Konversi id dari String ke Int saat menampilkan data
    id = this.id.toIntOrNull() ?: 0,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)