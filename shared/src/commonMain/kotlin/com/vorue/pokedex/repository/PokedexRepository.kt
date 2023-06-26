package com.vorue.pokedex.repository

import com.vorue.pokedex.core.PokedexService
import com.vorue.pokedex.data.network.Pokedex

class PokedexRepository : ImplementPokedexRepository {

    override suspend fun getPokedex() : Pokedex {
       return  PokedexService().get()
    }



    override suspend fun searchPokemon(name: String): Pokedex {
        return PokedexService().search(name)
    }

}