package com.vorue.pokedex.android.core

import com.vorue.pokedex.data.network.Pokedex
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexClient {

    //Agregar listado de peticiones dela API
    @GET("pokemon/?limit=800")
    suspend fun get() : Response<Pokedex>

    //Agregar buscador de pokemon
    @GET("pokemon/{pokemonName}")
    suspend fun searchPokemon(@Path("pokemonName") pokemonName: String) : Response<Pokedex>

    //Filtrar por tipo de pokemon
    @GET("type/{type_name}")
    suspend fun getPokemonByType(@Path("type_name") type: String) : Response<Pokedex>

}

