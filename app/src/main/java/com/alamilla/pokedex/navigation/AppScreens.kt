package com.alamilla.pokedex.navigation

sealed class AppScreens(val route: String){
    object SplashScreen: AppScreens("splash_screen")
    object Home: AppScreens("looking_for_pokemon")
    object DetailScreen: AppScreens("detail_screen")
    object PokemonListScreen: AppScreens("pokemon_list")
    object PokemonGpsScreen: AppScreens("pokemon_gps")
    object PokemonRandomScreen: AppScreens("pokemon_random_screen")
}
