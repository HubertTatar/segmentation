package io.huta.segmentation

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*

fun main() {

    //val repository = IOPlayerRepository()

    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        install(Routing) {
            get("/players") {
                call.respond(listOf(Player(1, "john", "doe")))
            }
        }
    }
    server.start(wait = true)
}