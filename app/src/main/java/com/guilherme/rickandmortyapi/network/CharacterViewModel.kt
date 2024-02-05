package com.guilherme.rickandmortyapi.network


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val TAG = "CharacterViewModel"

class CharacterViewModel : ViewModel() {

    private val repository = RickandMortyRepository()

    private val _characterItems: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val characterItem: StateFlow<List<Character>>
        get() = _characterItems

    init {
        viewModelScope.launch {
            try {
                val items = repository.fetchCharacters()
                Log.d(TAG, "Items received: $items")
                _characterItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch News items", ex)
            }
        }
    }
}