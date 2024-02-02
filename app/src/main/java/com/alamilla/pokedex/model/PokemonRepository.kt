package com.alamilla.pokedex.model

import javax.inject.Inject

class PokemonRepository @Inject constructor(private val apiPokemon: PokemonService) {

    suspend fun getPokemon(): PokemonModel? {
        val id = (0..100).random()
        return apiPokemon.getPokemon(id)
    }

    suspend fun getPokemonDetail(id: Int): DetailPokemonModel? {
        return apiPokemon.getPokemonDetail(id)
    }

    suspend fun getPokemonList(): PokemonListModel? {
        return apiPokemon.getPokemonList()
    }
}