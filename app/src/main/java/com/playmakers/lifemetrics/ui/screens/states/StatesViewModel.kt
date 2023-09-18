package com.playmakers.lifemetrics.ui.screens.states

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.playmakers.lifemetrics.data.ValuesRepository

class StatesViewModel(
    savedStateHandle: SavedStateHandle,
    private val valuesRepository: ValuesRepository
) : ViewModel() {

    companion object {
        fun saveTime(period: Long) {
            TODO("Not yet implemented")
        }

        fun deleteAllRecords(){
            TODO("Not yet implemented")
        }
    }
}