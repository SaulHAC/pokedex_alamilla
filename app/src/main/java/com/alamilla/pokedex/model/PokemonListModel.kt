package com.alamilla.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonListModel(
    @SerializedName("results") val results: List<Pokemon>,
)

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
