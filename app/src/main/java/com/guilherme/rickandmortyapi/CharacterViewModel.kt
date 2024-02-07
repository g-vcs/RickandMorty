package com.guilherme.rickandmortyapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.guilherme.rickandmortyapi.network.Character
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import com.guilherme.rickandmortyapi.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val TAG = "CharacterViewModel"

class CharacterViewModel : ViewModel() {

    private val repository = RickandMortyRepository()

    private var _characterItems: Flow<PagingData<Character>> = MutableStateFlow(PagingData.empty())
    val characterItem: Flow<PagingData<Character>>
        get() = _characterItems

/*    val characters: Flow<PagingData<Character>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { PagingSource(repository) }
        ).flow.cachedIn(viewModelScope)*/


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
