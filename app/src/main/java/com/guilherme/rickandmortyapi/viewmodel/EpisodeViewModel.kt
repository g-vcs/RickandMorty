package com.guilherme.rickandmortyapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.rickandmortyapi.model.Episode
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val TAG = "EpisodeViewModel"

class EpisodeViewModel: ViewModel(), KoinComponent {

    private val repository: RickandMortyRepository by inject()

    private val _episodeItem = MutableStateFlow<List<Episode>>(emptyList())
    val episodeItem: StateFlow<List<Episode>>
        get() = _episodeItem

    init {
        fetchAllEpisodes()
    }

    private fun fetchAllEpisodes() {
        viewModelScope.launch {
            try {
                val item = repository.fetchAllEpisodes()
                Log.d(TAG, "Episodes received: $item")
                _episodeItem.value = item

            } catch (ex: Exception){
                Log.e(TAG, "Failed to fetch all episodes", ex)
            }
        }
    }
}