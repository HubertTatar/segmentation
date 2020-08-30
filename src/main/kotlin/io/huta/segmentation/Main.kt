package io.huta.segmentation

import arrow.fx.IO
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

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
