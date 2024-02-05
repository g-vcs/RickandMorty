package com.guilherme.rickandmortyapi.network

data class CharacterResponse(
    val results: List<Character>
)

data class Character(
    val name: String,
    val image: String
)
