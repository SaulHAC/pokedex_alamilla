package com.alamilla.pokedex.feature.pokemonlist.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamilla.pokedex.model.DetailPokemonModel
import com.alamilla.pokedex.model.PokemonListModel
import com.alamilla.pokedex.model.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    val _pokemonDetail = MutableLiveData<PokemonListModel?>()

    fun onCreate() {
        viewModelScope.launch {
            val pokemonResponse = repository.getPokemonList()
            _pokemonDetail.postValue(pokemonResponse)
        }
    }
}