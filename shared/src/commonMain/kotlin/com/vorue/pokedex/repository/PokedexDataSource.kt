package com.vorue.pokedex.repository

import com.squareup.sqldelight.Query
import com.vorue.pokedex.DatabaseDriverFactory
import com.vorue.pokedex.data.network.PokedexResults
import com.vorue.pokedex.database.PokedexDatabase

class PokedexDataSource (databaseDriverFactory: DatabaseDriverFactory) : ImplementDataSource{
    private val database = PokedexDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.pokedexQueries



    override  fun insertPokedex(pokedexResults: PokedexResults) {
        dbQuery.transaction {
            dbQuery.insertPokedexResult(
                pokedexResults.id,
                pokedexResults.name,
                pokedexResults.url
            )
        }

    }

    override  fun updatePokedex(pokedexResults: PokedexResults) {
        dbQuery.transaction {
            dbQuery.updatePokedexResult(
                name =pokedexResults.name,
                url = pokedexResults.url,
                favorite = if (pokedexResults.favorite) 1 else 0,
                id = pokedexResults.id
            )
        }
    }

    override  fun deletePokedex(pokedexResults: PokedexResults) {
        dbQuery.transaction {
            dbQuery.deletePokedexResult(pokedexResults.id)
        }
    }

    override  fun searchPokedex(name_p : String): List<PokedexResults> {
        val results : List<PokedexResults> = dbQuery.searchPokedexResultName(name_p){name, url, id, favorite ->  PokedexResults(name, url, id) }.executeAsList()
        return results
    }

    override  fun getPokedex(): List<PokedexResults> {
        val results : List<PokedexResults> = dbQuery.getAllPokedexResults { name, url, id, favorite ->  PokedexResults(name, url, id ) }.executeAsList()
        return results
    }

    fun existPokedex(id : Long): Boolean {
        val bool = dbQuery.existsPokedexResult(id).executeAsOne()
        return bool
    }

     override  fun searchPokedex(id : Long): PokedexResults {
        val results : PokedexResults = dbQuery.searchPokedexResultID(id){ name, url, id, favorite ->  PokedexResults(name, url, id ,
            favorite != 0L)
        }.executeAsOne()
        return results
    }

}