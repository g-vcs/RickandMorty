package com.guilherme.rickandmortyapi.ui

sealed class Destination (val route: String) {
    data object CharacterScreen: Destination("characterScreen")
    data object CharInfoScreen: Destination("charInfoScreen")

}