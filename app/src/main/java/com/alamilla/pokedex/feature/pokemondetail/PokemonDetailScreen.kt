package com.alamilla.pokedex.feature.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.alamilla.pokedex.R
import com.alamilla.pokedex.feature.pokemondetail.viewmodel.DetailViewModel
import com.alamilla.pokedex.model.DetailPokemonModel
import com.alamilla.pokedex.navigation.AppScreens

@Composable
fun DetailPokemonScreen(navController: NavController, id: Int) {

    val detailViewModel: DetailViewModel = hiltViewModel()
    val pokemon by detailViewModel.pokemonModel.observeAsState(initial = null)

    LaunchedEffect(true) {
        detailViewModel.onCreate(id)
    }

    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFF0E2535),
                        Color(0xFF000000)
                    )
                )
            )
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 30.dp)
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF000000),
                            Color(0xFF0E2535),
                            Color(0xFF000000)
                        )
                    )
                )
        ) {
            Header(id) { navController.navigate(AppScreens.PokemonRandomScreen.route) }
            Spacer(modifier = Modifier.height(30.dp))
            ImagePokemon(pokemon, id)
        }
    }
}

@Composable
fun About(pokemon: DetailPokemonModel?) {
    Row(modifier = Modifier.padding(top = 10.dp, start = 30.dp, end = 30.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Row {
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 18.dp),
                    text = "About"
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = "Species",
                    color = Color(0xB7B3B8BD)
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = "Height",
                    color = Color(0xB7B3B8BD)
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = "Weight",
                    color = Color(0xB7B3B8BD)
                )
            }

            Row {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = "Abilities",
                    color = Color(0xB7B3B8BD)
                )
            }
        }
        Column(modifier = Modifier.weight(1.5f)) {
            Row {
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 18.dp),
                    text = ""
                )
            }
            Row {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = "${pokemon?.species?.name}"
                )
            }
            Row {
                Text(modifier = Modifier.padding(bottom = 14.dp), text = "${pokemon?.height} dm")
            }
            Row {
                Text(modifier = Modifier.padding(bottom = 14.dp), text = "${pokemon?.weight} lbs")
            }

            val abilities = pokemon?.abilities?.map { it.ability.name }
            val joinedAbilities = abilities?.joinToString(", ")

            Row {
                Text(modifier = Modifier.padding(bottom = 14.dp), text = "$joinedAbilities")
            }
        }
    }
}

@Composable
fun Stats(pokemon: DetailPokemonModel) {
    Column(modifier = Modifier.padding(top = 50.dp, start = 30.dp, end = 30.dp)) {
        Text(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 18.dp),
            text = "Base Stats"
        )

        Row() {
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    Text(
                        modifier = Modifier.padding(bottom = 14.dp),
                        text = "HP",
                        color = Color(0xB7B3B8BD)
                    )
                }
                Row {
                    Text(
                        modifier = Modifier.padding(bottom = 14.dp),
                        text = "Attack",
                        color = Color(0xB7B3B8BD)
                    )
                }
                Row {
                    Text(
                        modifier = Modifier.padding(bottom = 14.dp),
                        text = "Defense",
                        color = Color(0xB7B3B8BD)
                    )
                }

                Row {
                    Text(
                        modifier = Modifier.padding(bottom = 14.dp),
                        text = "Sp. Atk",
                        color = Color(0xB7B3B8BD)
                    )
                }

                Row {
                    Text(
                        modifier = Modifier.padding(bottom = 14.dp),
                        text = "Sp. Def",
                        color = Color(0xB7B3B8BD)
                    )
                }

                Row {
                    Text(
                        modifier = Modifier.padding(bottom = 14.dp),
                        text = "Speed",
                        color = Color(0xB7B3B8BD)
                    )
                }
            }
            Column(modifier = Modifier.weight(0.4f)) {
                pokemon?.stats?.forEach { stat ->

                    Text(
                        modifier = Modifier.padding(bottom = 14.dp),
                        text = stat.base_stat.toString(),
                        color = Color(0xB7B3B8BD)
                    )
                }
            }
            Column(modifier = Modifier.weight(1.5f)) {
                Spacer(modifier = Modifier.height(6.dp))

                pokemon?.stats?.forEach { stat ->
                    LinearProgressIndicator(
                        progress = minOf(stat.base_stat / 100f, 1f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(40.dp)),
                        trackColor = Color(0xFF817979),
                        color = if (stat.base_stat < 50) {
                            Color(0xFFDA2D20)
                        } else {
                            Color(0xFF3FB644)
                        }
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                }
            }
        }
    }
}

@Composable
fun ImagePokemon(pokemon: DetailPokemonModel?, id: Int) {
    Card(
        modifier = Modifier.fillMaxSize(), colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFF)
        )
    ) {
        if (pokemon != null) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = 30.dp),
                fontSize = 46.sp,
                text = pokemon.name,
                fontFamily = FontFamily(Font(R.font.righteous_regular))
            )

            Image(
                painter = rememberAsyncImagePainter(model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"),
                contentDescription = "",
                modifier = Modifier
                    .size(190.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(y = 45.dp)
                    .zIndex(1f)
            )

            Card(
                modifier = Modifier.fillMaxSize(), colors = CardDefaults.cardColors(
                    containerColor = Color(0x66050505)
                )
            ) {
                val scrollState = rememberScrollState()

                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Stats(pokemon)
                    About(pokemon)
                }
            }

        } else {
            CircularProgressIndicator(
                strokeWidth = 10.dp,
                color = Color.White,
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(30.dp)
            )
        }
    }
}

@Composable
fun Header(id: Int, onClickBack:() -> Unit) {
    Row {
        Card(modifier = Modifier.clickable {
            onClickBack()
        }, colors = CardDefaults.cardColors(containerColor = Color(0x2AFFFFFF))) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .padding(14.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF14B3E))) {
            Row(
                modifier = Modifier.padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "",
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = "#$id",
                    color = Color(0xFF201F1F),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 14.dp),
                    fontFamily = FontFamily(Font(R.font.righteous_regular))
                )
            }
        }
    }
}