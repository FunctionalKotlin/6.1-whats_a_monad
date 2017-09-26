// Copyright © FunctionalKotlin.com 2017. All rights reserved.

sealed class Option<out A>

object None : Option<Nothing>()

data class Just<out A>(val value: A) : Option<A>()

fun <A, B> Option<A>.map(transform: (A) -> B): Option<B> = when(this) {
    is None -> None
    is Just -> Just(transform(value))
}

fun <A> Option<A>.ifPresent(execute: (A) -> Unit) {
    if (this is Just) execute(this.value)
}