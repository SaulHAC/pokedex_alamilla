package com.alamilla.pokedex.model

import com.alamilla.pokedex.feature.pokemonradom.data.PokemonApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val apiClient: PokemonApiClient) {

    suspend fun getPokemon(id: Int): PokemonModel? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getPokemon(id)
            response.body()
        }
    }

    suspend fun getPokemonDetail(id: Int): DetailPokemonModel? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getPokemonDetail(id)
            response.body()
        }
    }

    suspend fun getPokemonList(): PokemonListModel? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getPokemonList()
            response.body()
        }
    }
}