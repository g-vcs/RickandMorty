package com.guilherme.rickandmortyapi.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.guilherme.rickandmortyapi.R
import com.guilherme.rickandmortyapi.ui.Destination


@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val modifier = Modifier


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painterResource(id = R.drawable.rick_and_morty),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                /*.clickable {
                    Toast.makeText(context, "Logo image clicked", Toast.LENGTH_SHORT).show()
                }*/
        )

        val characterScreen = ClickableImage(
            image = R.drawable.icons8_rick_sanchez,
            modifier = modifier,
            route = Destination.CharacterScreen.route,
            context = context,
            navController = navController
        )
        val locationScreen =
            ClickableImage(image = R.drawable.ic_launcher_background,modifier = modifier, route = "locationScreen", context, navController)
        val episodesScreen =
            ClickableImage(image = R.drawable.ic_launcher_background, modifier = modifier, route = "episodesScreen", context, navController)

    }

}

@Composable
fun ClickableImage(
    image: Int,
    modifier: Modifier,
    route: String,
    context: Context,
    navController: NavController
) {
    Image(
        painter = painterResource(image),
        contentDescription = null, // Provide proper content description
        modifier = modifier
            .size(200.dp)
            .padding(16.dp)
            .clip(CircleShape)
            .clickable {
                Toast.makeText(context, "Clicked in $route", Toast.LENGTH_SHORT).show()
                navController.navigate(route)
            },
        contentScale = ContentScale.Crop
    )
}
