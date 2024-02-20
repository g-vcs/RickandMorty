package com.guilherme.rickandmortyapi.ui

sealed class Destination (val route: String) {
    object characterScreen: Destination("characterScreen")
    object charInfoScreen: Destination("charInfoScreen")

}