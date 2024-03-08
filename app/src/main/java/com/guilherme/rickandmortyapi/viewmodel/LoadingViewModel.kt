package com.guilherme.rickandmortyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingViewModel:ViewModel() {

    init {
        viewModelScope.launch {
            delay(5000L)
        }
    }
}