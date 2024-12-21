package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.repository.RepositoryMk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


class HomeMkViewModel(
    private val repositoryMk: RepositoryMk,
) : ViewModel() {
    val homeMkUiState: StateFlow<HomeMkUiState> = repositoryMk.getAllMk()
        .filterNotNull()
        .map {
            HomeMkUiState(
                listMk = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeMkUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeMkUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMkUiState(
                isLoading = true,
            )
        )
}

data class HomeMkUiState(
    val listMk: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
)