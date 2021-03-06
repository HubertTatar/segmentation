package io.huta.segmentation

import arrow.Kind
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.extensions.fx

interface PlayerRepository<F> {
    fun findAll(): Kind<F, List<Player>>
}

class IOPlayerRepository : PlayerRepository<ForIO> {
    override fun findAll(): Kind<ForIO, List<Player>> = IO.fx {
        listOf(Player(1, "john", "doe"))
    }
}
