package com.radityalabs.android.corona.network

import com.radityalabs.android.corona.di.Module
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.LazyThreadSafetyMode.NONE

object NetworkModule {
    class Factory : Module() {
        private val okHttp by lazy(NONE) { OkHttpClient() }

        private val retrofit by lazy(NONE) {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .baseUrl("https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases/FeatureServer/1/query/")
                .build()
        }

        override fun provide() {
            provide<CovidService>(Factory().create())
        }

        fun create(): CovidService {
            return retrofit.create(CovidService::class.java)
        }
    }
}