package com.guilherme.rickandmortyapi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.guilherme.rickandmortyapi.di.db
import com.guilherme.rickandmortyapi.di.networkModule
import com.guilherme.rickandmortyapi.di.repositoryModule
import com.guilherme.rickandmortyapi.ui.ScreenNavigation
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(networkModule, repositoryModule, db))
        }

        setContent {
            ScreenNavigation()
        }
    }
}
