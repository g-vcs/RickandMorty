package com.guilherme.rickandmortyapi.network

import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.model.CharacterResponse
import com.guilherme.rickandmortyapi.model.EpisodeResponse
import com.guilherme.rickandmortyapi.model.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    suspend fun fetchCharacter(@Query("name") query: String?): CharacterResponse

    @GET("character")
    suspend fun fetchCharacterByPage(@Query("page") page: Int): Response<CharacterResponse>

    @GET("character/")
    suspend fun searchCharacters(@Query("name") query: String): CharacterResponse

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: String?): Character

    @GET("location")
    suspend fun fetchAllLocations(): LocationResponse

    @GET("episode")
    suspend fun fetchAllEpisodes(): EpisodeResponse
}
