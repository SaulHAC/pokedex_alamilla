package com.alamilla.pokedex.model

import com.google.gson.annotations.SerializedName

data class DetailPokemonModel(
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("sprites") val sprites: SpritesDetail,
    @SerializedName("id") val id: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("name") val name: String,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("species") val species: Species
)

data class Species(
    @SerializedName("name") val name: String
)

data class Stats(
    @SerializedName("base_stat") val base_stat: Int,
    @SerializedName("stat") val stat: Stat
)

data class Stat(
    @SerializedName("name") val name: String
)

data class Types(
    @SerializedName("type") val type: TypeName
)

data class TypeName(
    @SerializedName("name") val name: String
)

data class Ability(
    @SerializedName("ability") val ability: AbilityObject,
    @SerializedName("is_hidden") val is_hidden: String,
    @SerializedName("slot") val slot: String
)

data class AbilityObject(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class SpritesDetail(
    @SerializedName("other") val other: ArtworksDetail
)

data class ArtworksDetail(
    @SerializedName("official-artwork") val official_artwork: TypeArt
)
