package com.example.mydatasiswa.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydatasiswa.modeldata.DataSiswa
import com.example.mydatasiswa.repositori.RepositoryDataSiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoryDataSiswa
) : ViewModel() {

    private val siswaId: Int = checkNotNull(savedStateHandle["siswaId"]) // Ambil ID dari navigasi

    // Gunakan MutableStateFlow untuk menampung state
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // Ambil data siswa di dalam coroutine dan update state
            val siswa = repository.getSiswaById(siswaId)
            _uiState.value = DetailUiState(detailSiswa = siswa)
        }
    }

    fun deleteSiswa() {
        viewModelScope.launch {
            repository.deleteSiswa(siswaId)
        }
    }
}

data class DetailUiState(
    val detailSiswa: DataSiswa? = null
)