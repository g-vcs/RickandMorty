package com.guilherme.rickandmortyapi.network

import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.model.CharacterResponse
import com.guilherme.rickandmortyapi.model.Episode
import com.guilherme.rickandmortyapi.model.Location
import retrofit2.Response

class RickandMortyRepository(private val rickMortyApi: RickMortyApi) {

    suspend fun fetchCharacters(name: String?): List<Character> = rickMortyApi.fetchCharacter(name).results

    suspend fun getData(nextPageNumber: Int): Response<CharacterResponse> =
        rickMortyApi.fetchCharacterByPage(nextPageNumber)

    suspend fun getSearchData(nextPageNumber: Int, name: String?): Response<CharacterResponse> = rickMortyApi.fetchCharacterBySearch(nextPageNumber, name)

    suspend fun searchCharacters(nextPageNumber: Int, name: String): List<Character> = rickMortyApi.searchCharacters(nextPageNumber, name).results

    suspend fun getSingleCharacter(id: String?) = rickMortyApi.getSingleCharacter(id)

    suspend fun fetchAllLocations(): List<Location> = rickMortyApi.fetchAllLocations().results

    suspend fun fetchAllEpisodes() : List<Episode> = rickMortyApi.fetchAllEpisodes().results
}
