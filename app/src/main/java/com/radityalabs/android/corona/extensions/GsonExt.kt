package com.radityalabs.android.corona.extensions

import com.google.gson.Gson
import java.io.InputStreamReader

val gson = Gson()

inline fun <reified T> fromJson(file: String): T {
    val clazz = T::class.java
    val fixtureStreamReader = InputStreamReader(clazz.classLoader!!.getResourceAsStream(file))
    return gson.fromJson(fixtureStreamReader, clazz)
}
