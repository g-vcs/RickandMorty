package com.guilherme.rickandmortyapi.network

import retrofit2.Response

class RickandMortyRepository(private val rickMortyApi: RickMortyApi) {

    suspend fun fetchCharacters(): List<Character> = rickMortyApi.fetchCharacter().results

    suspend fun getData(nextPageNumber: Int): Response<CharacterResponse> =
        rickMortyApi.fetchCharacterByPage(nextPageNumber)
}
