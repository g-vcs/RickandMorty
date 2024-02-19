package com.guilherme.rickandmortyapi.di

import com.guilherme.rickandmortyapi.network.RickMortyApi
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import com.guilherme.rickandmortyapi.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val URL = "https://rickandmortyapi.com/api/"


val networkModule = module {
    single{
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickMortyApi::class.java)
    }
//    single { get<Retrofit>().create(RickMortyApi::class.java) }
}

val repositoryModule = module {
    single { RickandMortyRepository(get()) }
}

val appModule = module {
    viewModel {CharacterViewModel()}
}