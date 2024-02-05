package com.guilherme.rickandmortyapi.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    suspend fun fetchCharacter(): CharacterResponse

}