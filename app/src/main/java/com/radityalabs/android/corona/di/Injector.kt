@file:Suppress("UNCHECKED_CAST")

package com.radityalabs.android.corona.di

import java.util.concurrent.ConcurrentHashMap

object Injector {
    val maps = ConcurrentHashMap<Class<*>, Any>()

    inline fun <reified T> get(): T {
        return maps[T::class.java] as T
    }

    inline fun <reified T> add(obj: Any, clazz: Class<T>) {
        if (maps[clazz] != null) {
            maps.remove(clazz)
        }
        maps[clazz] = obj
    }

    fun add(obj: Any) {
        add(obj, obj.javaClass)
    }
}