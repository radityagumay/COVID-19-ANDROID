package com.radityalabs.android.corona.features.main.data

import com.radityalabs.android.corona.data.ConfirmedCaseRepository
import com.radityalabs.android.corona.di.Injector
import com.radityalabs.android.corona.network.CovidService
import com.radityalabs.android.corona.network.response.ConfirmedCaseResponse

interface MainRepository : ConfirmedCaseRepository {
    fun news(): List<String>
}

class MainRepositoryImpl(
    private val service: CovidService = Injector.get()
) : MainRepository {

    override suspend fun fetchConfirmedCovid(): List<ConfirmedCaseResponse> {
        TODO()
    }

    override fun news(): List<String> {
        TODO("Not yet implemented")
    }
}