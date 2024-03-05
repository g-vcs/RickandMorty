package com.guilherme.rickandmortyapi.viewmodel

import android.provider.CallLog
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.guilherme.rickandmortyapi.paging.PagingSource
import com.guilherme.rickandmortyapi.model.Location
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val TAG = "LocationViewModel"

class LocationViewModel : ViewModel(), KoinComponent {

    private val repository: RickandMortyRepository by inject()

    private val _locationItems= MutableStateFlow<List<Location>>(emptyList())

    val locationItem: StateFlow<List<Location>> = _locationItems


    init {
        fetchAllLocations()
    }


    private fun fetchAllLocations() {
        viewModelScope.launch {
            try {
                val locationsItems = repository.fetchAllLocations()
                Log.d(TAG, "Location received: $locationsItems")
                _locationItems.value = locationsItems

            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch all location", ex)
            }
        }
    }


}