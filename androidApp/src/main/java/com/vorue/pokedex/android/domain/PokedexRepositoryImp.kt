package com.vorue.pokedex.android.domain

import com.vorue.pokedex.android.core.PokedexClient
import com.vorue.pokedex.data.network.Pokedex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokedexRepositoryImp(private val pokedexClient: PokedexClient) : PokedexRepository {


    override suspend fun getPokedex(): Response<Pokedex> {
        return withContext(Dispatchers.IO) {
            pokedexClient.get()
        }
    }

    //Agregar buscador de pokemon
    override suspend fun searchPokemon(pokemonName: String): Response<Pokedex> {
        return withContext(Dispatchers.IO) {
            pokedexClient.searchPokemon(pokemonName)
        }
    }

    //Agregar filtrado por tipo de pokemon
    //override suspend fun getPokemonByType(type: String): Response<Pokedex> {
    //    return withContext(Dispatchers.IO) {
    //        pokedexClient.getPokemonByType(type)
    //    }
    //}
}