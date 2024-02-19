package com.guilherme.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guilherme.rickandmortyapi.di.networkModule
import com.guilherme.rickandmortyapi.di.repositoryModule
import com.guilherme.rickandmortyapi.ui.CharactersScreen
import com.guilherme.rickandmortyapi.viewmodel.CharacterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(networkModule, repositoryModule))
        }

//        setContentView(R.layout.activity_main)

        setContent {
            CharactersScreen()
        }
    }
}
