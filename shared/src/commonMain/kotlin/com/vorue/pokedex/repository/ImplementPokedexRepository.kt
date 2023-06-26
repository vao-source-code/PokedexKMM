package com.vorue.pokedex.repository

import com.vorue.pokedex.data.network.Pokedex

interface ImplementPokedexRepository {

    suspend fun getPokedex(): Pokedex


    suspend fun searchPokemon(name: String): Pokedex

}