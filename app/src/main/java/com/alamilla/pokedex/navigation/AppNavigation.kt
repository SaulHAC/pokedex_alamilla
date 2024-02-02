package com.alamilla.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alamilla.pokedex.feature.pokemondetail.DetailPokemonScreen
import com.alamilla.pokedex.feature.pokemongps.PokemonGpsScreen
import com.alamilla.pokedex.feature.pokemonlist.PokemonList
import com.alamilla.pokedex.feature.home.HomePokemonScreen
import com.alamilla.pokedex.feature.pokemonradom.PokemonRandomScreen
import com.alamilla.pokedex.feature.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navControllerSplash = rememberNavController()
    NavHost(navController = navControllerSplash, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navControllerSplash)
        }
        composable(AppScreens.Home.route) {
            HomePokemonScreen()
        }
    }
}