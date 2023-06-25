package com.vorue.pokedex.android.data.network.pokemon

import com.google.gson.annotations.SerializedName
import com.vorue.pokedex.data.network.pokemon.Abilities
import com.vorue.pokedex.data.network.pokemon.Ability
import com.vorue.pokedex.data.network.pokemon.Move
import com.vorue.pokedex.data.network.pokemon.Moves
import com.vorue.pokedex.data.network.pokemon.Species
import com.vorue.pokedex.data.network.pokemon.Sprites
import com.vorue.pokedex.data.network.pokemon.Stat
import com.vorue.pokedex.data.network.pokemon.Stats
import com.vorue.pokedex.data.network.pokemon.Type
import com.vorue.pokedex.data.network.pokemon.Types


data class Pokemon(
    @SerializedName("abilities") var abilities: ArrayList<Abilities> = arrayListOf(),
    @SerializedName("base_experience") var baseExperience: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("moves") var moves: ArrayList<Moves> = arrayListOf(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("past_types") var pastTypes: ArrayList<String> = arrayListOf(),
    @SerializedName("species") var species: Species? = Species(),
    @SerializedName("sprites") var sprites: Sprites? = Sprites(),
    @SerializedName("stats") var stats: ArrayList<Stats> = arrayListOf(),
    @SerializedName("types") var types: ArrayList<Types> = arrayListOf(),
    @SerializedName("weight") var weight: Int? = null
)

data class Abilities(

    @SerializedName("ability") var ability: Ability? = Ability(),
    @SerializedName("is_hidden") var isHidden: Boolean? = null,
    @SerializedName("slot") var slot: Int? = null

)

data class Ability(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class Moves(
    @SerializedName("move") var move: Move? = Move()
)

data class Move(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class Species(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class Sprites(

    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null

)
data class Stat(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)
data class Stats(

    @SerializedName("base_stat") var baseStat: Int? = null,
    @SerializedName("effort") var effort: Int? = null,
    @SerializedName("stat") var stat: Stat? = Stat()

)

data class Type(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class Types(
    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: Type? = Type()
)

