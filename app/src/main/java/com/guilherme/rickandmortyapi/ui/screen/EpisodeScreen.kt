package com.guilherme.rickandmortyapi.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.guilherme.rickandmortyapi.viewmodel.EpisodeViewModel

const val TAG = "EpisodeScreen"

@Composable
fun EpisodeScreen(
    viewModel: EpisodeViewModel = viewModel()
) {
    val episodeItem by viewModel.episodeItem.collectAsState()

    Log.d(TAG, "info passed: ${episodeItem[0].name}")


    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

        }
        AsyncImage(
            model = episodeItem,
            contentDescription = "Character Image",
            modifier = Modifier
                .size(200.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )


    }


}