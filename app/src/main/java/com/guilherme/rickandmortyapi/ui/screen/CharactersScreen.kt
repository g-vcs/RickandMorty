package com.guilherme.rickandmortyapi.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.guilherme.rickandmortyapi.R
import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.model.Location
import com.guilherme.rickandmortyapi.model.Origin
import com.guilherme.rickandmortyapi.ui.Destination
import com.guilherme.rickandmortyapi.viewmodel.CharacterViewModel
import java.util.Locale
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun CharactersScreen(viewModel: CharacterViewModel = viewModel(), navController: NavController) {
    //val context = LocalContext.current
    val characterPagingItems: LazyPagingItems<Character> =
        viewModel.searchResult.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painterResource(id = R.drawable.rick_and_morty),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )

        }
        SearchBar(onSearchTextChanged(viewModel), modifier = Modifier)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()

        ) {
            val loadState = characterPagingItems.loadState
            when (loadState.prepend) {
                is Loading -> {
                    item { LoadingItem() }
                }

                is LoadState.Error -> {
                    item { ErrorItem() }
                }

                else -> {
                    items(characterPagingItems.itemCount) { index ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(8.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(5.dp)
                                )
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RectangleShape)
                                    .clickable {
                                        navController.navigate(Destination.CharInfoScreen.route + "/${characterPagingItems[index]?.id}")
                                    }
                            ) {
                                AsyncImage(
                                    model = characterPagingItems[index]?.image,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(32.dp, 32.dp, 32.dp, 32.dp))
                                        .padding(8.dp)
                                    //.fillMaxSize()
                                )
                            }
                            Text(
                                text = characterPagingItems[index]?.name ?: "Unknown",
                                color = Color.Gray,
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Row(
                                modifier = Modifier.padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 6.dp)
                                        .clip(CircleShape)
                                        .size(12.dp)
                                        .background(
                                            getStatusColor(
                                                characterPagingItems[index]?.status ?: "Unknown"
                                            )
                                        )
                                )

                                Text(
                                    text = characterPagingItems[index]?.status ?: "Unknown",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center // Adjust as needed
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun onSearchTextChanged(viewModel: CharacterViewModel): (String?) -> Unit {
    return { searchText: String? ->
        if (searchText != null) {
            viewModel.searchCharacters(searchText)
            Log.d("CharacterViewModel", "Search text changed: $searchText")

        }
        println("Search text changed: $searchText")
    }
}

@Composable
fun SearchBar(
    onSearchTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onSearchTextChanged(it.text)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Search") },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchTextChanged(text.text)
            }
        )
    )
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorItem() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text("Error loading data")
    }
}

@Composable
fun getStatusColor(status: String): Color {
    return when (status.toLowerCase(Locale.getDefault())) {
        "alive" -> Color.Green
        "dead" -> Color.Red
        else -> Color.Gray
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListPreview() {
    val sampleCharacters = listOf(
        Character(
            id = 1,
            name = "Rick",
            status = "Alive",
            image = "image_url_1",
            created = "",
            //episode = emptyList(),
            gender = "",
            /*location = Location(
                created = "",
                dimension = "",
                id = 0,
                name = "",
                residents = listOf(),
                type = "",
                url = ""
            ),
            origin = Origin("", ""),
            species = "",
            type = "",
            url = ""*/
        ),
        Character(
            id = 2,
            name = "Morty",
            status = "Alive",
            image = "image_url_2",
            created = "",
            //episode = emptyList(),
            gender = "",
            /*location = Location(
                created = "",
                dimension = "",
                id = 0,
                name = "",
                residents = listOf(),
                type = "",
                url = ""
            ),
            origin = Origin("", ""),
            species = "",
            type = "",
            url = ""*/
        ),
        Character(
            id = 3,
            name = "Summer",
            status = "Unknown",
            image = "image_url_3",
            created = "",
            //episode = emptyList(),
            gender = "",
            /*location = Location(
                created = "",
                dimension = "",
                id = 0,
                name = "",
                residents = listOf(),
                type = "",
                url = ""
            ),
            origin = Origin("", ""),
            species = "",
            type = "",
            url = ""*/
        ),
        // Add more sample characters as needed
    )

    val navController = rememberNavController()
    CharactersScreen(navController = navController)

}


