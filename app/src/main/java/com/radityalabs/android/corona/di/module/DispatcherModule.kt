package com.radityalabs.android.corona.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

internal const val IO = "DISPATCHER_IO"
internal const val MAIN = "DISPATCHER_MAIN"
internal const val DEFAULT = "DISPATCHER_DEFAULT"

@Module
@InstallIn(ApplicationComponent::class)
internal object DispatcherModule {
    @Provides
    @Singleton
    @Named(IO)
    internal fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @Named(MAIN)
    internal fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @Named(DEFAULT)
    internal fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
