package com.playmaker.quitly.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.playmaker.quitly.QuitlyApplication
import com.playmaker.quitly.ui.stateModel.MainViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainViewModel(
                QuitlyApplication().userPreferencesRepository,
                QuitlyApplication().container.timesRepository
            )
        }
    }
}

// Without this, the lateinit var in the QuitlyApplication is not initialized and that makes error
fun CreationExtras.QuitlyApplication(): QuitlyApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuitlyApplication )