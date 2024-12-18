package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Dosen

data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
)
