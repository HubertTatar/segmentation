package io.huta.segmentation

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

class PlayerRouting<F>(
    private val repository: PlayerRepository<F>,
    suspendable: Suspendable<F>
): Suspendable<F> by suspendable {
    fun Routing.players(path: String) {
        get(path) {
            call.respond(repository.findAll().suspended())
        }
    }
}