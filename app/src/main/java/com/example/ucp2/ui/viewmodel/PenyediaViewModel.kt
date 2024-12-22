package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KampusApp

object PenyediaMkViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                kampusApp().containerApp.repositoryDsn,
            )
        }
        initializer {
            HomeDsnViewModel(
                kampusApp().containerApp.repositoryDsn,
            )
        }
        initializer {
            MataKuliahViewModel(
                kampusApp().containerApp.repositoryMk,
                kampusApp().containerApp.repositoryDsn
            )
        }
        initializer {
            HomeMkViewModel(
                kampusApp().containerApp.repositoryMk,
            )
        }
        initializer {
            DetailMkViewModel(
                createSavedStateHandle(),
                kampusApp().containerApp.repositoryMk,
            )
        }
        initializer {
            UpdateMkViewModel(
                createSavedStateHandle(),
                kampusApp().containerApp.repositoryMk,
                kampusApp().containerApp.repositoryDsn
            )
        }
    }
}


fun CreationExtras.kampusApp() : KampusApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KampusApp)