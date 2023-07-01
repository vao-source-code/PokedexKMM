package com.vorue.pokedex.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Pokedex(
    @SerialName(value = "count")
    val count: Int? ,
    @SerialName(value = "next")
    val next: String?,
    @SerialName(value = "previous")
    val previous: String?,
    @SerialName(value = "results")
    val results: List<PokedexResults>
)
