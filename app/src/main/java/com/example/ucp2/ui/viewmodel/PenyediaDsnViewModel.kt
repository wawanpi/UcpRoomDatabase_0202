package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KampusApp

object PenyediaDsnViewModel{
    val Factory = viewModelFactory {

        initializer {
            HomeDsnView(
                kampusApp().containerApp.repositoryDsn
            )
        }

    }
}

fun CreationExtras.kampusApp() : KampusApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KampusApp)