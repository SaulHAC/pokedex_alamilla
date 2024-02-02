package com.alamilla.pokedex.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alamilla.pokedex.R
import com.alamilla.pokedex.feature.pokemondetail.DetailPokemonScreen
import com.alamilla.pokedex.feature.pokemongps.PokemonGpsScreen
import com.alamilla.pokedex.feature.pokemonlist.PokemonList
import com.alamilla.pokedex.feature.pokemonradom.PokemonRandomScreen
import com.alamilla.pokedex.feature.pokemonradom.viewmodel.PokemonViewModel
import com.alamilla.pokedex.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePokemonScreen() {

    var index by remember { mutableIntStateOf(1) }
    val navController = rememberNavController()

    val pokemonViewModel: PokemonViewModel = hiltViewModel()

    Scaffold(

        floatingActionButton = {
            MyFAB(selectedIndex = index,
                onTabSelected = { newIndex ->
                    index = newIndex
                    when (newIndex) {
                        1 -> navController.navigate(AppScreens.PokemonRandomScreen.route)
                    }
                }, new = { pokemonViewModel.newRandomPokemon() })
        },
        bottomBar = {
            MyBottomNavigation(
                selectedIndex = index,
                onTabSelected = { newIndex ->
                    index = newIndex
                    when (newIndex) {
                        0 -> navController.navigate(AppScreens.PokemonListScreen.route)
                        2 -> navController.navigate(AppScreens.PokemonGpsScreen.route)
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,

        ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White)
                .fillMaxSize()
        ) {

            NavHost(
                navController = navController,
                startDestination = AppScreens.PokemonRandomScreen.route
            ) {
                composable(AppScreens.PokemonRandomScreen.route) {
                    PokemonRandomScreen(navController)
                }
                composable(
                    "${AppScreens.DetailScreen.route}/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { backStackEntry ->
                    DetailPokemonScreen(navController, backStackEntry.arguments?.getInt("id") ?: 0)
                }
                composable(AppScreens.PokemonListScreen.route) {
                    PokemonList()
                }
                composable(AppScreens.PokemonGpsScreen.route) {
                    PokemonGpsScreen(navController)
                }
            }
        }
    }
}

@Composable
fun MyBottomNavigation(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    NavigationBar(containerColor = Color(0xFFF3F3F3), contentColor = Color.Transparent) {
        NavigationBarItem(colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color(0xFFFFFFFF),
            selectedTextColor = Color(0xFFC21D1D),
            indicatorColor = Color(0xFFF15668),
            unselectedIconColor = Color(0xFF9EA7AC),
            unselectedTextColor = Color(0xFF999EA0)
        ),
            selected = selectedIndex == 0,
            onClick = {
                if (selectedIndex != 0) {
                    onTabSelected(0)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "home"
                )
            },
            label = {
                Text(
                    text = "Pixelarts",
                    fontFamily = FontFamily(Font(R.font.righteous_regular))
                )
            })

        Spacer(modifier = Modifier.weight(0.5f))

        NavigationBarItem(colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color(0xFFFFFFFF),
            selectedTextColor = Color(0xFFC21D1D),
            indicatorColor = Color(0xFFF15668),
            unselectedIconColor = Color(0xFF9EA7AC),
            unselectedTextColor = Color(0xFF999EA0)
        ),
            selected = selectedIndex == 2,
            onClick = {
                if (selectedIndex != 2) {
                    onTabSelected(2)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "home"
                )
            },
            label = { Text(text = "GPS", fontFamily = FontFamily(Font(R.font.righteous_regular))) })
    }
}

@Composable
fun MyFAB(selectedIndex: Int, onTabSelected: (Int) -> Unit, new: () -> Unit) {
    FloatingActionButton(
        onClick = {
            if (selectedIndex != 1) {
                onTabSelected(1)
            } else {
                new()
            }

        },
        containerColor = Color.Black,
        contentColor = Color.Black,
        shape = CircleShape,
        modifier = Modifier
            .offset(y = 60.dp)
            .size(80.dp),
    ) {
        Image(painter = painterResource(id = R.drawable.pokeball_home), contentDescription = "")
    }
}