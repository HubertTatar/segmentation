package io.huta.segmentation

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

class PlayerRouting<F>(
    private val repository: PlayerRepository<F>,
    suspendable: Suspendable<F>
) : Suspendable<F> by suspendable {
    fun Routing.players(path: String) {
        get(path) {
            call.respond(repository.findAll().suspended())
        }
    }
}
