package com.guilherme.rickandmortyapi.ui

sealed class Destination (val route: String) {

    data object EpisodeScreen: Destination("episodeScreen")
    data object HomeScreen: Destination("homeScreen")
    data object CharacterScreen: Destination("characterScreen")
    data object CharInfoScreen: Destination("charInfoScreen")
    data object LocationScreen: Destination("locationScreen")
    data object LoadingScreen: Destination("loadingScreen")


}