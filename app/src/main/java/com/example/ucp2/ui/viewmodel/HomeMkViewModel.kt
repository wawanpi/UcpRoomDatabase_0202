package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.MataKuliah


data class HomeMkUiState(
    val listMhs: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
)