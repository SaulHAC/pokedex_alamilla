package com.alamilla.pokedex.feature.pokemondetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamilla.pokedex.model.DetailPokemonModel
import com.alamilla.pokedex.model.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {

    val pokemonModel = MutableLiveData<DetailPokemonModel?>()

    fun onCreate(id: Int) {
        viewModelScope.launch {
            val pokemon = repository.getPokemonDetail(id)
            pokemonModel.postValue(pokemon)
        }
    }
}