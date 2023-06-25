package com.vorue.pokedex.data.network.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerialName("abilities") var abilities: ArrayList<Abilities> = arrayListOf(),
    @SerialName("base_experience") var baseExperience: Int? = null,
    @SerialName("height") var height: Int? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("moves") var moves: ArrayList<Moves> = arrayListOf(),
    @SerialName("name") var name: String? = null,
    @SerialName("order") var order: Int? = null,
    @SerialName("past_types") var pastTypes: ArrayList<String> = arrayListOf(),
    @SerialName("species") var species: Species? = Species(),
    @SerialName("sprites") var sprites: Sprites? = Sprites(),
    @SerialName("stats") var stats: ArrayList<Stats> = arrayListOf(),
    @SerialName("types") var types: ArrayList<Types> = arrayListOf(),
    @SerialName("weight") var weight: Int? = null
)

@Serializable
data class Abilities(

    @SerialName("ability") var ability: Ability? = Ability(),
    @SerialName("is_hidden") var isHidden: Boolean? = null,
    @SerialName("slot") var slot: Int? = null

)

@Serializable
data class Ability(

    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)

@Serializable
data class Moves(
    @SerialName("move") var move: Move? = Move()
)

@Serializable
data class Move(

    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)

@Serializable
data class Species(

    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)

@Serializable
data class Sprites(

    @SerialName("back_default") var backDefault: String? = null,
    @SerialName("front_default") var frontDefault: String? = null

)

@Serializable
data class Stat(
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null
)

@Serializable
data class Stats(
    @SerialName("base_stat") var baseStat: Int? = null,
    @SerialName("effort") var effort: Int? = null,
    @SerialName("stat") var stat: Stat? = Stat()
)

@Serializable
data class Type(
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null
)

@Serializable
data class Types(
    @SerialName("slot") var slot: Int? = null,
    @SerialName("type") var type: Type? = Type()
)

