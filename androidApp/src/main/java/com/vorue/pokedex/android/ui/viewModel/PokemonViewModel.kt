package com.example.pokedex.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vorue.pokedex.android.data.network.PokedexResults

class PokemonViewModel : ViewModel() {

    val pokemon = MutableLiveData<PokedexResults>()


    fun setPokemon(id: Int) {
        //val pokemon = repository.getPokemon(id)
        if (pokemon != null)
       //     this.pokemon.postValue(pokemon)
        else
            Log.d("PokemonViewModel", "Error retrieving pokemon: ")
         //   this.pokemon.postValue(null)
    }

}