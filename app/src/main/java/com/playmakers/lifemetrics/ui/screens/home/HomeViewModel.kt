package com.playmakers.lifemetrics.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val isShowStateScreen = mutableStateOf(false)

    fun showStateScreen(){
        isShowStateScreen.value = true
    }

    fun showHomeScreen(){
        isShowStateScreen.value = false
    }
}