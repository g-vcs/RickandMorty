package com.guilherme.rickandmortyapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.model.Location
import com.guilherme.rickandmortyapi.model.Origin
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import com.guilherme.rickandmortyapi.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
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

    val singleCharacter: StateFlow<Character?>
        get() = _singleCharacter
    private val _singleCharacter: MutableStateFlow<Character?> = MutableStateFlow(Character())

    val _search: MutableStateFlow<List<Character>?> = MutableStateFlow(emptyList())
    val search: StateFlow<List<Character>?>
        get() = _search

    init {
        fetchAllCharacters()
        searchCharacters("Alien")

    }

    private fun fetchAllCharacters() {
        viewModelScope.launch {
            try {
                val items = repository.fetchCharacters("")
                Log.d(TAG, "Character received: $items")
                _characterItems = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
                    pagingSourceFactory = { PagingSource(repository) }
                ).flow.cachedIn(viewModelScope)
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch Character list items", ex)
            }
        }
    }

    fun fetchSingleCharacter(id: String) {
        viewModelScope.launch {
            try {
                val character = repository.getSingleCharacter(id)
                Log.d(TAG, "this character received: $character")
                _singleCharacter.value = character

            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch Single Character ", ex)

            }
        }
    }

    fun searchCharacters(name: String){
        viewModelScope.launch {
            try {
                val currentSearch = repository.fetchCharacters(name)
                Log.d(TAG, "Search character received: $currentSearch")
                _search.value = currentSearch
            } catch (ex: Exception){
                Log.e(TAG, "Failed to search Characters ", ex)
            }
        }
    }

}
