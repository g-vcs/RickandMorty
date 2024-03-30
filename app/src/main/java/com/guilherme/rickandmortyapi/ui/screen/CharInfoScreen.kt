package com.guilherme.rickandmortyapi.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.guilherme.rickandmortyapi.model.LifeState
import com.guilherme.rickandmortyapi.viewmodel.CharacterViewModel

@Composable()
fun  CharInfoScreen(
    viewModel: CharacterViewModel = viewModel(),
    characterId:String,
    ) {
    val TAG = "CharInfoScreen"

    LaunchedEffect(characterId) {
        viewModel.fetchSingleCharacter(characterId)
    }

    val singleCharacter by viewModel.singleCharacter.collectAsState()

    Log.d(TAG, "info passed: ${singleCharacter?.name}")

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = singleCharacter?.image,
                contentDescription = "Character Image",
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                //text = singleCharacter.name,
                text = "name",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Status: ${singleCharacter?.status}",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Species: ${singleCharacter?.species}",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Gender: ${singleCharacter?.gender}",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            /*Text(
                text = "Location: ${singleCharacter?.location?.name}",
                fontSize = 18.sp
            )*/
            Spacer(modifier = Modifier.height(8.dp))
            /*Text(
                text = "Origin: ${singleCharacter?.origin?.name}",
                fontSize = 18.sp
            )*/
        }
    }
}
