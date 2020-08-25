package com.radityalabs.android.corona.features.main.presentation.sheet

import com.radityalabs.android.corona.di.module.IO
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IsUnderTest

@Module
@InstallIn(FragmentComponent::class)
internal object BottomSheetModule {
    @Provides
    @FragmentScoped
    fun providesVm(
        usecase: BottomSheetUseCase,
        @Named(IO) io: CoroutineDispatcher
    ) = BottomSheetViewModel(usecase, io)

    @Provides
    @FragmentScoped
    @IsUnderTest
    fun providesUnderTest(): Boolean = false

    @dagger.Module
    @InstallIn(FragmentComponent::class)
    internal abstract class Module {
        @Binds
        @FragmentScoped
        abstract fun bindsUseCase(impl: BottomSheetUseCaseImpl): BottomSheetUseCase

        @Binds
        @FragmentScoped
        abstract fun bindsRepository(impl: BottomSheetRepositoryImpl): BottomSheetRepository
    }
}