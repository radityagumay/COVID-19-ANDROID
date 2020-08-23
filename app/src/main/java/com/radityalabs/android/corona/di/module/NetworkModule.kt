package com.radityalabs.android.corona.di.module

import com.radityalabs.android.corona.network.CovidService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    internal fun providesOkHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    internal fun providesRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .baseUrl("https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases/FeatureServer/1/query/")
            .build()
    }

    @Provides
    @Singleton
    internal fun providesService(retrofit: Retrofit): CovidService {
        return retrofit.create(CovidService::class.java)
    }
}
