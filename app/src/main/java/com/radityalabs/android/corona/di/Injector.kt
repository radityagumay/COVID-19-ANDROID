@file:Suppress("UNCHECKED_CAST")

package com.radityalabs.android.corona.di

import java.util.concurrent.ConcurrentHashMap

object Injector {
    val maps: MutableMap<Class<*>, Any> = ConcurrentHashMap()

    inline fun <reified T> get(): T {
        return maps[T::class.java] as T
    }

    inline fun <reified T : Module> provide() {
        try {
            T::class.java.newInstance().provide()
        } catch (e: Throwable) {
            throw IllegalStateException("Cannot process", e)
        }
    }

    fun add(clazz: Class<*>, obj: Any) {
        if (maps.containsKey(clazz)) {
            maps.remove(clazz)
        }
        maps[clazz] = obj
    }

    fun remove(any: Any) {
        maps.remove(any.javaClass)
    }

    fun add(obj: Any) {
        add(obj.javaClass, obj)
    }
}