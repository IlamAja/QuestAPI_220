package com.example.mydatasiswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydatasiswa.modeldata.DetailSiswa
import com.example.mydatasiswa.modeldata.UIStateSiswa
import com.example.mydatasiswa.modeldata.toDataSiswa
import com.example.mydatasiswa.modeldata.toDetailSiswa
import com.example.mydatasiswa.repositori.RepositoryDataSiswa
import com.example.mydatasiswa.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val siswaId: Int = checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])

    init {
        viewModelScope.launch {
            repository.getSiswaById(siswaId)?.let {
                uiStateSiswa = UIStateSiswa(detailSiswa = it.toDetailSiswa())
            }
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            repository.updateSiswa(siswaId, uiStateSiswa.detailSiswa.toDataSiswa())
        }
    }
}