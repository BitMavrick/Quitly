package com.playmakers.lifemetrics.ui.screens.states

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.playmakers.lifemetrics.data.ValuesRepository

class StatesViewModel(
    savedStateHandle: SavedStateHandle,
    private val valuesRepository: ValuesRepository
) : ViewModel() {
}