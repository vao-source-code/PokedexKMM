package com.vorue.pokedex.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//Agregar listado de peticiones dela API
@Serializable
data class PokedexResults(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String,
    val id: Long = url.split('/')[6].toLong(),
    var favorite : Boolean = false
)
