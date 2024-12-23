package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.repository.RepositoryMk
import com.example.ucp2.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMkViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk,
    private val repossioryDsn: RepositoryDsn
) : ViewModel() {
    // List of dosen
    var dosentList by mutableStateOf<List<Dosen>>(emptyList())
        private set

    var updateUiState by mutableStateOf(MkUiState())
        private set
    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdate.KODE])

    init {
        // Get Mata Kuliah data
        viewModelScope.launch {
            updateUiState = repositoryMk.getMk(_kode)
                .filterNotNull()
                .first()
                .toUIStateMk()
        }

        // Get Dosen list and update UI state
        viewModelScope.launch {
            val dosentListFromRepo = repossioryDsn.getAllDsn().first()
            updateUiState = updateUiState.copy(dosentList = dosentListFromRepo)
        }
    }

    // Update state with MataKuliahEvent
    fun updateState(mataKuliahEvent: MataKuliahEvent) {
        updateUiState = updateUiState.copy(
            mataKuliahEvent = mataKuliahEvent,
        )
    }

    // Validate input fields
    fun validateFields(): Boolean {
        val event = updateUiState.mataKuliahEvent
        val errorState = MkFormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) {
                if (event.sks.toIntOrNull() != null) null else "SKS harus berupa angka"
            } else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Kelas tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )
        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Update MataKuliah data
    fun updateData() {
        val currentEvent = updateUiState.mataKuliahEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMk.updateMk(currentEvent.toMataKuliahEntity())
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data Berhasil diupdate",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = MkFormErrorState(),
                    )
                } catch (e: Exception) {
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data Gagal diupdate"
                    )
                }
            }
        } else {
            updateUiState = updateUiState.copy(
                snackBarMessage = "Data gagal di update"
            )
        }
    }

    // Reset Snackbar message
    fun resetSnackBarMessage() {
        updateUiState = updateUiState.copy(snackBarMessage = null)
    }
}

fun MataKuliah.toUIStateMk(): MkUiState = MkUiState(
    mataKuliahEvent = this.toDetailUiEvent(),
)
