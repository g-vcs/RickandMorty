package com.guilherme.rickandmortyapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)

@Entity
data class Character(
    @PrimaryKey val id: Int?= null,
    @ColumnInfo val created: String ?= null,
    //val episode: List<String>?= null,
    @ColumnInfo val gender: String?= null,
    @ColumnInfo val image: String?= null,
    //val location: Location?= null,
    @ColumnInfo val name: String ?= null,
    //val origin: Origin?= null,
    @ColumnInfo val species: String?= null,
    @ColumnInfo val status: String?= null,
    @ColumnInfo val type: String?= null,
    @ColumnInfo val url: String?= null
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
