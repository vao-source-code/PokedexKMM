package com.vorue.pokedex.android.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vorue.pokedex.data.network.PokedexResults
import com.vorue.pokedex.data.network.pokemon.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    val pokemon = MutableLiveData<PokedexResults>()


    fun setPokemon(id: Int) {
       /*viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                val pokedexRepository =  PokedexRepository()
                pokedexRepository.getPokemon(id)
            }.onSuccess {
                if (it!= null) {
                    pokemon.postValue(it)
                }
            }.onFailure {
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
            }
        }*/
    }

}