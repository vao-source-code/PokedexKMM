package com.vorue.pokedex.repository

import com.vorue.pokedex.DatabaseDriverFactory
import com.vorue.pokedex.data.network.PokedexResults
import com.vorue.pokedex.database.PokedexDatabase

class PokedexDataSource (databaseDriverFactory: DatabaseDriverFactory){
    private val database = PokedexDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.pokedexQueries



    fun insertPokedex(pokedexResults: PokedexResults) {

        dbQuery.transaction {
            dbQuery.insertPokedexResult(
                pokedexResults.id,
                pokedexResults.name,
                pokedexResults.url,
                if (pokedexResults.favorite) 1 else 0
            )
        }

    }

    fun updatePokedex(pokedexResults: PokedexResults) {
        dbQuery.transaction {
            dbQuery.updatePokedexResult(
                name =pokedexResults.name,
                url = pokedexResults.url,
                favorite = if (pokedexResults.favorite) 1 else 0,
                id = pokedexResults.id
            )
        }

    }

    fun deletePokedex(pokedexResults: PokedexResults) {
        dbQuery.transaction {
            dbQuery.deletePokedexResult(pokedexResults.id)

        }
    }

    fun searchPokedex(name_p : String): List<PokedexResults> {
        val results : List<PokedexResults> = dbQuery.searchPokedexResultName(name_p){name, url, id, favorite ->  PokedexResults(name, url, id, favorite == 0L) }.executeAsList()
        return results
    }

    fun getPokedex(): List<PokedexResults> {
        val results : List<PokedexResults> = dbQuery.getAllPokedexResults { name, url, id, favorite ->  PokedexResults(name, url, id, favorite == 0L) }.executeAsList()
        return results
    }

}