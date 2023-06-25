package com.vorue.pokedex.android.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vorue.pokedex.android.domain.factory.PokedexScreenState
import com.vorue.pokedex.data.network.Pokedex
import com.vorue.pokedex.android.domain.PokedexRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(private val pokedexRepository: PokedexRepository) : ViewModel() {

    val pokedex = MutableLiveData<Pokedex>()

    private val _screenState: MutableStateFlow<PokedexScreenState> = MutableStateFlow(
        PokedexScreenState.Loading
    )
    val screenState: Flow<PokedexScreenState> = _screenState

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("PokedexViewModel", "Error retrieving pokedex: ${throwable.message}")
        }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                pokedexRepository.getPokedex()
            }.onSuccess {
                if (it.body() != null) {
                    pokedex.postValue(it.body()!!)
                    _screenState.value = PokedexScreenState.ShowPokedex(it.body()!!)
                } else {
                    _screenState.value = PokedexScreenState.Error
                }
            }.onFailure {
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
                _screenState.value = PokedexScreenState.Error
            }

        }
    }

    //Agregar buscador de pokemon
    /*
      fun searchPokemon(pokemonName: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                pokedexRepository.searchPokemon(pokemonName)
            }.onSuccess {
                if (it.body() != null) {
                    pokedex.postValue(it.body()!!)
                    _screenState.value = PokedexScreenState.ShowPokedex(it.body()!!)
                } else {
                    _screenState.value = PokedexScreenState.Error
                }
            }.onFailure {
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
                _screenState.value = PokedexScreenState.Error
            }

        }
    }
     */


}
