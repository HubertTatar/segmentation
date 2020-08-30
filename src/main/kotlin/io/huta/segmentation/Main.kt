package io.huta.segmentation

import arrow.fx.IO
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {

    val playerRouting = PlayerRouting(IOPlayerRepository(), IO.suspendable())

    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        install(Routing) {
            with(playerRouting) { players("/players") }
        }
    }
    server.start(wait = true)
}