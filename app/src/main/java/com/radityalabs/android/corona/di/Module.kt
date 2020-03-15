package com.radityalabs.android.corona.di

abstract class Module {
    abstract fun provide()

    inline fun <reified T> provide(instance: Any) {
        Injector.add(T::class.java, instance)
    }

    fun provide(clazz: Class<*>, instance: Any) {
        Injector.add(clazz, instance)
    }
}