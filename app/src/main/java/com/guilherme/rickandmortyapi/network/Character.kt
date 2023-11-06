package com.guilherme.rickandmortyapi.network

import com.squareup.moshi.Json

data class Character(
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String
)

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