package com.vorue.pokedex.repository

import com.vorue.pokedex.data.network.PokedexResults

interface ImplementDataSource {

    suspend fun insertPokemon(id : Int, name: String, url: String , favorite: Boolean)
    suspend fun deletePokemon(name: String)
    suspend fun getAllPokemon(): List<PokedexResults>
    suspend fun getPokemon(name: String): PokedexResults
    suspend fun searchPokemon(name: String): PokedexResults
    suspend fun updatePokemon(name: String, url: String)
}