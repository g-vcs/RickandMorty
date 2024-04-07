package com.guilherme.rickandmortyapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.guilherme.rickandmortyapi.db.AppDatabase
import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.model.CharacterResponse
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
//import com.guilherme.rickandmortyapi.paging.PagingSearchSource
import com.guilherme.rickandmortyapi.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "CharacterViewModel"

class CharacterViewModel : ViewModel(), KoinComponent {

    private val repository: RickandMortyRepository by inject()
    private val db: AppDatabase by inject()

    private var _characterItems: Flow<PagingData<Character>> = MutableStateFlow(PagingData.empty())
    val characterItem: Flow<PagingData<Character>>
        get() = _characterItems


    val singleCharacter: StateFlow<Character?>
        get() = _singleCharacter
    private val _singleCharacter: MutableStateFlow<Character?> = MutableStateFlow(Character())

    private val _isSearchShowing = MutableStateFlow(false)

    val isSearchShowing = _isSearchShowing.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )



    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "",
        )




    init {
        fetchAllCharacters()
        //searchCharacters("Alien")
    }

    private fun fetchAllCharacters() {
        viewModelScope.launch {
            try {
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
                withContext(Dispatchers.IO){
                    db.characterDao().insertCharacter(character)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch Single Character ", ex)

            }
        }
    }

    fun searchCharacters(name: String){
        Log.d(TAG, "name is : $name")
//        _characterItems = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
//            pagingSourceFactory = { PagingSource(repository, name) }
//        ).flow.cachedIn(viewModelScope)
        setSearch(name)
    }


    val searchResult = search.debounce(300.milliseconds).flatMapLatest { query ->
        Pager(
            PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            )
        ) {
            PagingSource(
                repository = repository,
                name = query,
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun setSearch(query: String) {
        _search.value = query
    }

}
