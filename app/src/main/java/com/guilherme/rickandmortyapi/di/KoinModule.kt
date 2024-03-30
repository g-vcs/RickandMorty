package com.guilherme.rickandmortyapi.di

import android.util.Log
import androidx.room.Room
import com.guilherme.rickandmortyapi.db.AppDatabase
import com.guilherme.rickandmortyapi.network.RickMortyApi
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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
}

val repositoryModule = module {
    single { RickandMortyRepository(get()) }
}

val db = module {
    Log.d("RICK", "database created")
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "characters-db"
        ).build()
    }
}

