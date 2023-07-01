package com.vorue.pokedex.core

import com.vorue.pokedex.data.network.PokedexResults
import com.vorue.pokedex.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokedexClient {

    /*
    La clase HttpClientProvider proporciona una instancia de HttpClient con configuraciones de registro y negociación de contenido.
     El HttpClient permite realizar solicitudes HTTP, registrar las solicitudes y respuestas, y manejar objetos JSON.
     */
    object HttpClientProvider {

        val httpClient = HttpClient {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "HttpClient", message = message)
                    }
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }.also {
            initLogger()
        }
    }

}