package com.guilherme.rickandmortyapi.model

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)

data class Origin(
    val name: String,
    val url: String
)

data class LocationResponse(
    val info: Info,
    val results: List<Location>
)

data class Location(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)

data class EpisodeResponse(
    val info: Info,
    val results: List<Episode>
)

data class Episode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)
