package com.vorue.pokedex.android.data.network

import com.google.gson.annotations.SerializedName
import com.vorue.pokedex.data.network.PokedexResults

data class Pokedex(
    @SerializedName(value = "count")
    val count: Int,
    @SerializedName(value = "next")
    val next: String,
    @SerializedName(value = "previous")
    val previous: String,
    @SerializedName(value = "results")
    val results: List<PokedexResults>
)
