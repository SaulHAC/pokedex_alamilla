package com.alamilla.pokedex.feature.pokemongps.viewmodel

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamilla.pokedex.model.PokemonModel
import com.alamilla.pokedex.model.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    val pokemonModel = MutableLiveData<PokemonModel?>()
    val vibrate = MutableLiveData(false)

    fun showPokemon() {
        Log.i("10m", "superaste 10 metros")
        viewModelScope.launch {
            vibrate.value = true
            val pokemon = repository.getPokemon()
            pokemonModel.value = pokemon
        }
    }

    fun vibrate(vibrator: Vibrator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(500)
        }
    }

    fun changeVibrateValue(){
        vibrate.value = false
    }
}