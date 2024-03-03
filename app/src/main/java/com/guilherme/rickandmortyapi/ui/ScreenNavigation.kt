package com.guilherme.rickandmortyapi.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun ScreenNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.CharacterScreen.route) {
        composable(
            route = Destination.CharacterScreen.route
        ) { CharactersScreen(navController = navController) }

        composable(
            route = Destination.CharInfoScreen.route,
        ) {
            CharInfoScreen()
        }
    }
}