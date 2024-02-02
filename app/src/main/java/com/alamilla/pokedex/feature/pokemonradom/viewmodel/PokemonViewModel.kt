package com.alamilla.pokedex.feature.pokemonradom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamilla.pokedex.model.PokemonModel
import com.alamilla.pokedex.model.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    val pokemonModel = MutableLiveData<PokemonModel?>()

    fun onCreate() {
        viewModelScope.launch {
            val pokemon = repository.getPokemon()
            pokemonModel.value = pokemon
        }
    }

    fun newRandomPokemon() {
        pokemonModel.value = null
        viewModelScope.launch {
            val pokemon = repository.getPokemon()
            pokemonModel.postValue(pokemon)
        }
    }
}