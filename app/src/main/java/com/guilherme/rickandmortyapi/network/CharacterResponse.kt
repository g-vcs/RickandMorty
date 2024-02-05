package com.guilherme.rickandmortyapi.network

data class CharacterResponse(
    val results: List<Character>
)
data class Character(
    val name: String,
    val image: String
)

<<<<<<< HEAD:app/src/main/java/com/guilherme/rickandmortyapi/network/CharacterResponse.kt
=======
data class CharacterResponse(
    @Json(name = "results")
    val result: List<Character>
)

data class Info(
    @Json(name = "next")
    val next: String,
    @Json(name = "previous")
    val previous: String
)

data class InfoResponse(
    @Json(name = "info")
    val info: List<Info>
)
>>>>>>> main:app/src/main/java/com/guilherme/rickandmortyapi/network/Character.kt
