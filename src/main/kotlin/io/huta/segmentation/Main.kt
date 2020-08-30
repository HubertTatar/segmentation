package io.huta.segmentation

import arrow.core.Either
import arrow.fx.extensions.io.applicativeError.attempt
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*

fun main() {

    val repository = IOPlayerRepository()

    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        install(Routing) {
            get("/players") {
                when (val result = repository.findAll().attempt().unsafeRunSync()) {
                    is Either.Right -> call.respond(result.b)
                    else -> call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
    server.start(wait = true)
}