package com.alamilla.pokedex.feature.pokemonradom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.alamilla.pokedex.R
import com.alamilla.pokedex.feature.pokemonradom.viewmodel.PokemonViewModel
import com.alamilla.pokedex.model.PokemonModel
import com.alamilla.pokedex.navigation.AppScreens

@Composable
fun PokemonRandomScreen(navController: NavHostController) {

    val pokemonViewModel: PokemonViewModel = hiltViewModel()

    val pokemon by pokemonViewModel.pokemonModel.observeAsState(initial = null)

    var pokemonId: Int = 0

    LaunchedEffect(true) {
        pokemonViewModel.onCreate()
    }

    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFF0F6080),
                        Color(0xFF000000)
                    )
                )
            )
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            ImagePokemon(pokemon = pokemon, onClick = { newIndex -> pokemonId = newIndex })
            Spacer(modifier = Modifier.height(50.dp))
            ButtonRandomPokemon(
                navigateToDetail = { navController.navigate("${AppScreens.DetailScreen.route}/$pokemonId") },
                newPokemon = { pokemonViewModel.newRandomPokemon() })
        }
    }
}

@Composable
fun ImagePokemon(pokemon: PokemonModel?, onClick: (Int) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(220.dp), colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFF)
        )
    ) {
        if (pokemon == null) {
            CircularProgressIndicator(
                strokeWidth = 10.dp,
                color = Color.White,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
        } else {
            onClick(pokemon.id)
            Image(
                // painter = rememberAsyncImagePainter(model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                painter = rememberAsyncImagePainter(model = pokemon!!.sprites.other.official_artwork.front_default),
                contentDescription = "",
                modifier = Modifier
                    .size(220.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colorFilter = ColorFilter.tint(color = Color.Black)

            )
        }
    }
}

@Composable
fun ButtonRandomPokemon(navigateToDetail: () -> Unit, newPokemon: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFF))
    ) {
        Button(
            onClick = { navigateToDetail() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF19D181)
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp,
                text = "Revelar pokemon",
                fontFamily = FontFamily(Font(R.font.righteous_regular))
            )
        }

        Spacer(modifier = Modifier.height(70.dp))

        Button(
            onClick = {
                newPokemon()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFC5E5A)
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp,
                text = "Obtener otro",
                fontFamily = FontFamily(Font(R.font.righteous_regular))
            )
        }
    }
}
