package com.guilherme.rickandmortyapi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import com.guilherme.rickandmortyapi.network.Character
import kotlinx.coroutines.flow.Flow

@Composable
fun CharactersScreen(
    characterItems:  PagingData<Character>
){
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        characterItems

    }

}