package com.alamilla.pokedex.feature.pokemonradom.data

import com.alamilla.pokedex.model.DetailPokemonModel
import com.alamilla.pokedex.model.PokemonListModel
import com.alamilla.pokedex.model.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiClient {
    @GET("{pokemonId}")
    suspend fun getPokemon(@Path("pokemonId") randomNumber: Int): Response<PokemonModel>

    @GET("{pokemonId}")
    suspend fun getPokemonDetail(@Path("pokemonId") id:Int): Response<DetailPokemonModel>

    @GET("?limit=50")
    suspend fun getPokemonList(): Response<PokemonListModel>
}