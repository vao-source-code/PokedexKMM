package com.vorue.pokedex.repository

import com.vorue.pokedex.data.network.PokedexResults

interface ImplementDataSource {
     fun insertPokedex(pokedexResults: PokedexResults)
     fun updatePokedex(pokedexResults: PokedexResults)
     fun deletePokedex(pokedexResults: PokedexResults)
     fun searchPokedex(name_p : String): List<PokedexResults>
     fun searchPokedex(id : Long): PokedexResults
     fun getPokedex(): List<PokedexResults>

}