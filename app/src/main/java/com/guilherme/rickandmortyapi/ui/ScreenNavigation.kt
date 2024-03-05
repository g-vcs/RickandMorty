package com.guilherme.rickandmortyapi.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilherme.rickandmortyapi.ui.screen.CharInfoScreen
import com.guilherme.rickandmortyapi.ui.screen.CharactersScreen
import com.guilherme.rickandmortyapi.ui.screen.HomeScreen
import com.guilherme.rickandmortyapi.ui.screen.LocationScreen


@Composable
fun ScreenNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.HomeScreen.route) {
        composable(
            route = Destination.HomeScreen.route
        ) { HomeScreen(navController = navController) }

        composable(
            route = Destination.CharacterScreen.route
        ) { CharactersScreen(navController = navController) }

        composable(
            route = Destination.CharInfoScreen.route +"/{characterId}"
        ) {
            val data = it.arguments?.getString("characterId")
            CharInfoScreen(characterId = data)
        }

        composable(
            route = Destination.LocationScreen.route
        ) { LocationScreen() }
    }
}