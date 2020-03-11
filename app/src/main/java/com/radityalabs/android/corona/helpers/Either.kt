package com.radityalabs.android.corona.helpers

sealed class Either<out E, out V> {
    data class Failure<out E>(val error: E) : Either<E, Nothing>()
    data class Success<out V>(val value: V) : Either<Nothing, V>()
}

fun <V> value(value: V): Either<Nothing, V> = Either.Success(value)

fun <E> failure(value: E): Either<E, Nothing> = Either.Failure(value)

fun <E, V> Either<E, V>.value(): V {
    return (this as Either.Success).value
}
