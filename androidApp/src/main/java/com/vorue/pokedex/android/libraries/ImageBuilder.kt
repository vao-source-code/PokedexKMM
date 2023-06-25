package com.vorue.pokedex.android.libraries

object ImageBuilder {

    //Funcion que trae las imagenes por id
    fun buildPokemonImageByUrl(detailUrl: String): String {
        val pokemonId = detailUrl.split('/')[6]
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"
    }
}