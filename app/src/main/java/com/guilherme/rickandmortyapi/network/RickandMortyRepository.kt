package com.guilherme.rickandmortyapi.network

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
}

