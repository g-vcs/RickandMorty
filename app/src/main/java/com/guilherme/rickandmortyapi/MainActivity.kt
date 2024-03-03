package com.guilherme.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilherme.rickandmortyapi.di.networkModule
import com.guilherme.rickandmortyapi.di.repositoryModule
import com.guilherme.rickandmortyapi.ui.CharInfoScreen
import com.guilherme.rickandmortyapi.ui.CharactersScreen
import com.guilherme.rickandmortyapi.ui.Destination.CharInfoScreen
import com.guilherme.rickandmortyapi.ui.Destination.CharacterScreen
import com.guilherme.rickandmortyapi.ui.ScreenNavigation
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(networkModule, repositoryModule))
        }

        setContent {
            ScreenNavigation()
        }
    }
}
