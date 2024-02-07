package com.guilherme.rickandmortyapi.network

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.guilherme.rickandmortyapi.paging.PagingSource
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RickandMortyRepository {
    private val newsAPi: RickMortyApi
    private val URL = "https://rickandmortyapi.com/api/"

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsAPi = retrofit.create()
    }

    suspend fun fetchCharacters(): List<Character> = newsAPi.fetchCharacter().results


    suspend fun getData(nextPageNumber: Int): Response<CharacterResponse> = newsAPi.fetchCharacterByPage(nextPageNumber)

}
