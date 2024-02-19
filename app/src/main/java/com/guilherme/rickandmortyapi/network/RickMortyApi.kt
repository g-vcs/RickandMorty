package com.guilherme.rickandmortyapi.network

import com.guilherme.rickandmortyapi.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    suspend fun fetchCharacter(): CharacterResponse

    @GET("character")
    suspend fun fetchCharacterByPage(@Query("page") page: Int): Response<CharacterResponse>

}
