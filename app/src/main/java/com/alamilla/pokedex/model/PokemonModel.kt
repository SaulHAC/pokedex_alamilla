package com.alamilla.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)

data class Sprites(
    @SerializedName("other") val other: Artworks
)

data class Artworks(
    @SerializedName("official-artwork") val official_artwork: TypeArt
)

data class TypeArt(
    @SerializedName("front_default") val front_default: String
)