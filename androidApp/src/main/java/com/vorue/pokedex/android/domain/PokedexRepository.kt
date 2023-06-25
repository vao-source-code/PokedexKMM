package com.vorue.pokedex.android.domain

import com.vorue.pokedex.android.data.network.Pokedex
import retrofit2.Response

interface PokedexRepository {

    //Agregar listado de peticiones dela API

    suspend fun getPokedex() : Response<Pokedex>

    suspend fun searchPokemon(pokemonName: String) : Response<Pokedex>


}