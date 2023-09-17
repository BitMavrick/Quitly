package com.playmakers.lifemetrics.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.playmakers.lifemetrics.LifeMetricsApplication
import com.playmakers.lifemetrics.ui.screens.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                lifeMetricsApplication().userPreferencesRepository
            )
        }
    }
}

fun CreationExtras.lifeMetricsApplication(): LifeMetricsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LifeMetricsApplication )