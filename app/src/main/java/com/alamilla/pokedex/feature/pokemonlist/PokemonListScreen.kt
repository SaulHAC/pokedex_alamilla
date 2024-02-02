package com.alamilla.pokedex.feature.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.alamilla.pokedex.R
import com.alamilla.pokedex.feature.pokemonlist.viewmodel.ListViewModel
import com.alamilla.pokedex.model.Pokemon
import com.alamilla.pokedex.model.PokemonListModel

@Composable
fun PokemonList() {

    val listPokemonsViewModel: ListViewModel = hiltViewModel()
    val pokemonList by listPokemonsViewModel._pokemonDetail.observeAsState(initial = null)

    LaunchedEffect(true) {
        listPokemonsViewModel.onCreate()
    }

    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFF1B415C),
                        Color(0xFF000000)
                    )
                )
            )
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color(0xFFBDBABA),
                    fontSize = 46.sp,
                    text = "Pixelarts",
                    fontFamily = FontFamily(Font(R.font.righteous_regular))
                )

                
            }

            if (pokemonList != null) {
                RecyclerView(pokemonList!!)
            } else {
                CircularProgressIndicator(
                    strokeWidth = 10.dp,
                    color = Color.White,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun RecyclerView(pokemonList: PokemonListModel) {

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList.results) { pokemon ->

            val url = pokemon.url
            val parts = url.split("/")
            val idPokemon = parts[parts.size - 2].toIntOrNull()
            if (idPokemon != null) {
                ItemPokemon(pokemon, idPokemon)
            }
        }
    }
}

@Composable
fun ItemPokemon(pokemon: Pokemon, idPokemon: Int) {
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(0.dp))
    ) {
        Column {
            Image(
                rememberAsyncImagePainter(model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/$idPokemon.png"),
                contentDescription = "",
                modifier = Modifier
                    .size(140.dp)
                    .padding(4.dp)
            )
            Text(color = Color(0xB7FFFFFF), modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 12.sp, text = pokemon.name, fontFamily = FontFamily(Font(R.font.pressstart2p_regular)))
        }
    }
}
