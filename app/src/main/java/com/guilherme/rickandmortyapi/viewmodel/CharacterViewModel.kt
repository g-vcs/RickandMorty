package com.guilherme.rickandmortyapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import com.guilherme.rickandmortyapi.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val TAG = "CharacterViewModel"

class CharacterViewModel : ViewModel(), KoinComponent {

    private val repository: RickandMortyRepository by inject()
    private var _characterItems: Flow<PagingData<Character>> = MutableStateFlow(PagingData.empty())

    val characterItem: Flow<PagingData<Character>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 2)) {
            PagingSource(repository)
        }.flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            try {
                val items = repository.fetchCharacters()
                Log.d(TAG, "Items received: $items")
                _characterItems = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
                    pagingSourceFactory = { PagingSource(repository) }
                ).flow.cachedIn(viewModelScope)
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch News items", ex)
            }
        }
    }
}
