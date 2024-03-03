package com.guilherme.rickandmortyapi.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.guilherme.rickandmortyapi.model.Character


@Composable()
fun  CharInfoScreen(/*navController: NavController, navBackStackEntry: NavBackStackEntry*/) {
    val TAG = "CharInfoScreen"
    /*val characterId = navBackStackEntry.arguments?.getString("characterId")
    Log.d(TAG, "info passed: $characterId")*/
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