package com.vorue.pokedex.android.domain.factory

import com.vorue.pokedex.android.data.network.Pokedex

//Utiliza una clase para verificar si esta cargando o bien si hay un error
sealed class PokedexScreenState {
    object Loading : PokedexScreenState()

    object Error : PokedexScreenState()

    class ShowPokedex(val pokedex : Pokedex) : PokedexScreenState()
}