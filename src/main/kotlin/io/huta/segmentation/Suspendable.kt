package io.huta.segmentation

import arrow.Kind
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.fix

interface Suspendable<F> {
    suspend fun <A : Any> Kind<F, A>.suspended(): A
}

fun IO.Companion.suspendable(): Suspendable<ForIO> = object : Suspendable<ForIO> {
    override suspend fun <A : Any> Kind<ForIO, A>.suspended(): A {
        val io = this.fix()
        return io.suspended()
    }
}
