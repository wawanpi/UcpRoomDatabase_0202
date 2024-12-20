package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMk
import com.example.ucp2.ui.navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMkViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk,
) : ViewModel(){
    private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetail.KODE])

    val detailUiState: StateFlow<DetailUiState> = repositoryMk.getMk(_kode)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    erorMessage = it.message ?: "Terjadi Kesalahan",

                    )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )

    fun deleteMk(){
        detailUiState.value.detailUiEvent.toMataKuliahEntity().let {
            viewModelScope.launch {
                repositoryMk.deleteMk(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent :MataKuliahEvent = MataKuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val erorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MataKuliahEvent()

    val isEventNotEmpty: Boolean
        get() = detailUiEvent != MataKuliahEvent()

}

/* Data class untuk menampung data yang akan ditampilkan di UI*/

//memindahkan data dari entity ke ui
fun MataKuliah.toDetailUiEvent(): MataKuliahEvent{
    return MataKuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu
    )
}
