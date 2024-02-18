package com.guilherme.rickandmortyapi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.guilherme.rickandmortyapi.network.Character
import com.guilherme.rickandmortyapi.viewmodel.CharacterViewModel
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun CharactersScreen(
    characterItems: PagingData<Character>,
    viewModel: CharacterViewModel
) {
    var count by remember {
        mutableStateOf(0)
    }

    val characterPagingItems: LazyPagingItems<Character> =
        viewModel.characterItem.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(characterPagingItems.itemCount) { index ->
            Text(
                text = characterPagingItems[index]!!.name,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    CharactersScreen()
}