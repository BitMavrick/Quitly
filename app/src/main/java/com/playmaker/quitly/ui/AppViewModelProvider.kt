package com.playmaker.quitly.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.playmaker.quitly.QuitlyApplication
import com.playmaker.quitly.ui.stateModel.MainViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainViewModel(
                QuitlyApplication().userPreferencesRepository
            )
        }
    }
}