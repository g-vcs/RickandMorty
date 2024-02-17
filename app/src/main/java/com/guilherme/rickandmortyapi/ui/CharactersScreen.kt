package com.guilherme.rickandmortyapi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import com.guilherme.rickandmortyapi.network.Character
import kotlinx.coroutines.flow.Flow

@Composable
fun CharactersScreen(
//    characterItems:  PagingData<Character>
){
    var count by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            fontSize = 30.sp
        )
        Button(onClick = {
            count++

        }) {
            Text(text = "Click Me")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharactersScreen()
}