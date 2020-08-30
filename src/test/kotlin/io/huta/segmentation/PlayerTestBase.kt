package io.huta.segmentation

import arrow.Kind
import arrow.core.ForId
import arrow.core.Id
import arrow.core.fix
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing

class MockPlayerRepository(private val list: List<Player>) : PlayerRepository<ForId> {
    override fun findAll(): Id<List<Player>> = Id.just(list)
}

fun Id.Companion.suspendable(): Suspendable<ForId> = object : Suspendable<ForId> {
    override suspend fun <A : Any> Kind<ForId, A>.suspended(): A {
        val id = this.fix()
        return id.extract()
    }
}

val players = listOf(Player(1, "john", "doe"))
val routingForUnitTests = PlayerRouting(MockPlayerRepository(players), Id.suspendable())

fun Application.testApp() {
    install(ContentNegotiation) {
        jackson {}
    }
    install(Routing) {
        with(routingForUnitTests) { players("/test/players") }
    }
}
