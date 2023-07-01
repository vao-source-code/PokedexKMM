package com.vorue.pokedex

import com.squareup.sqldelight.db.SqlDriver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initLogger()

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}