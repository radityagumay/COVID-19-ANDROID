package com.radityalabs.android.corona.features.main.data

import com.radityalabs.android.corona.di.Injector
import com.radityalabs.android.corona.extensions.Either
import com.radityalabs.android.corona.extensions.failure
import com.radityalabs.android.corona.extensions.value
import com.radityalabs.android.corona.network.CovidService
import com.radityalabs.android.corona.network.model.response.ConfirmedCaseResponse

interface MainRepository {
    suspend fun fetchConfirmedCase(): Either<Throwable, ConfirmedCaseResponse>
}

class MainRepositoryImpl(
    private val service: CovidService = Injector.get()
) : MainRepository {

    override suspend fun fetchConfirmedCase(): Either<Throwable, ConfirmedCaseResponse> {
        return runCatching {
            value(service.getConfirmedCase())
        }.getOrElse { error ->
            failure(error)
        }
    }
}