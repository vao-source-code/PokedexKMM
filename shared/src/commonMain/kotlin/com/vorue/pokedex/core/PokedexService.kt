package com.vorue.pokedex.core

import com.vorue.pokedex.data.network.Pokedex
import com.vorue.pokedex.data.network.PokedexResults
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokedexService {

    suspend fun get() : Pokedex {
        val result =  PokedexClient.HttpClientProvider.httpClient.get("https://pokeapi.co/api/v2/pokemon/?limit=800")
        return result.body()
    }

    suspend fun search(query: String) : Pokedex {
        val result =  PokedexClient.HttpClientProvider.httpClient.get("https://pokeapi.co/api/v2/pokemon/$query")
        return result.body()
    }

    suspend fun getPokemonByType(type: String) : Pokedex {
        val result =  PokedexClient.HttpClientProvider.httpClient.get("https://pokeapi.co/api/v2/type/$type")
        return result.body()
    }

}