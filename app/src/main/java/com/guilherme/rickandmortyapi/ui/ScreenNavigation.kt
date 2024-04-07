package com.guilherme.rickandmortyapi.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilherme.rickandmortyapi.ui.screen.CharInfoScreen
import com.guilherme.rickandmortyapi.ui.screen.CharactersScreen
import com.guilherme.rickandmortyapi.ui.screen.EpisodeScreen
import com.guilherme.rickandmortyapi.ui.screen.HomeScreen
import com.guilherme.rickandmortyapi.ui.screen.LoadingScreen
import com.guilherme.rickandmortyapi.ui.screen.LocationScreen


@Composable
fun ScreenNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.CharacterScreen.route) {
        composable(
            route = Destination.HomeScreen.route
        ) { HomeScreen(navController = navController) }

        composable(
            route = Destination.CharacterScreen.route
        ) { CharactersScreen(navController = navController) }


        composable(
            route = "${Destination.CharInfoScreen.route}/{characterId}"
        ) { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getString("characterId")
            characterId?.let { CharInfoScreen(characterId = it) }
        }

        composable(
            route = Destination.LocationScreen.route
        ) { LocationScreen() }

        composable(
            route = Destination.EpisodeScreen.route
        ) { EpisodeScreen() }

        composable(
            route = Destination.LoadingScreen.route
        ) { LoadingScreen(navController = navController) }
    }
}