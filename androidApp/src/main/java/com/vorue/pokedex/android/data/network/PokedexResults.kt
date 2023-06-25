package com.vorue.pokedex.android.data.network

import com.google.gson.annotations.SerializedName

//Agregar listado de peticiones dela API
data class PokedexResults(
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "url")
    val url: String,
    val id: Int = url.split('/')[6].toInt()
)
