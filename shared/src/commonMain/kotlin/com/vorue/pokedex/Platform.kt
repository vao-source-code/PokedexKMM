package com.vorue.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform