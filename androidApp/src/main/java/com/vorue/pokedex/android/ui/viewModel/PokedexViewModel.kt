package com.vorue.pokedex.android.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vorue.pokedex.DatabaseDriverFactory
import com.vorue.pokedex.android.domain.factory.PokedexScreenState
import com.vorue.pokedex.data.network.Pokedex
import com.vorue.pokedex.core.PokedexService
import com.vorue.pokedex.repository.PokedexDataSource
import com.vorue.pokedex.repository.PokedexRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(context: Context) : ViewModel() {

    val pokedex = MutableLiveData<Pokedex>()

    private val _screenState: MutableStateFlow<PokedexScreenState> = MutableStateFlow(
        PokedexScreenState.Loading
    )
    val screenState: Flow<PokedexScreenState> = _screenState

     var pokedexDataSource : PokedexDataSource
    init {
         pokedexDataSource = PokedexDataSource(DatabaseDriverFactory(context))

    }

    companion object {
        const val favorites = "menu_key_favorites"
    }

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("PokedexViewModel", "Error retrieving pokedex: ${throwable.message}")
        }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                val pokedexRepository =  PokedexService()
                pokedexRepository.get()
            }.onSuccess {
                if (it != null) {
                    it.results.forEach {
                        pokedexDataSource.insertPokedex(it)
                    }
                    pokedex.postValue(it)
                    _screenState.value = PokedexScreenState.ShowPokedex(it)
                } else {
                    _screenState.value = PokedexScreenState.Error
                    val pokedexEror = Pokedex(
                        count = 0,
                        next = "",
                        previous = "",
                        results = pokedexDataSource.getPokedex()
                    )
                    pokedex.postValue(pokedexEror)

                }
            }.onFailure {
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
                _screenState.value = PokedexScreenState.Error
            }

        }
    }

    //Agregar buscador de pokemon

      fun searchPokemon(pokemonName: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                val pokedexRepository =  PokedexService()
                pokedexRepository.search(pokemonName)
            }.onSuccess {
                if (it!= null) {
                    pokedex.postValue(it)
                    _screenState.value = PokedexScreenState.ShowPokedex(it)
                } else {
                    _screenState.value = PokedexScreenState.Error
                }
            }.onFailure {
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
                _screenState.value = PokedexScreenState.Error
            }

        }
    }



}
