package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KampusApp

object PenyediaMkViewModel{
    val Factory = viewModelFactory {
        initializer {
            MataKuliahViewModel(
                kampusApp().containerApp.repositoryMk
            )
        }
        initializer {
            HomeMkViewModel(
                mkApp().containerApp.repositoryMk
            )
        }
        initializer {
            DetailMkViewModel(
                createSavedStateHandle(),
                mkApp().containerApp.repositoryMk,
            )
        }
        initializer {
            UpdateMkViewModel(
                createSavedStateHandle(),
                mkApp().containerApp.repositoryMk
            )
        }
    }
}


fun CreationExtras.mkApp() : KampusApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KampusApp)