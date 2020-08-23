package com.radityalabs.android.corona.features.main.data

import com.radityalabs.android.corona.extensions.Either
import com.radityalabs.android.corona.extensions.failure
import com.radityalabs.android.corona.extensions.value
import com.radityalabs.android.corona.network.CovidService
import com.radityalabs.android.corona.network.model.response.ConfirmedCaseResponse
import javax.inject.Inject

interface MainRepository {
    suspend fun fetchConfirmedCase(): Either<Throwable, ConfirmedCaseResponse>
}

class MainRepositoryImpl @Inject constructor(
    private val service: CovidService
) : MainRepository {

    override suspend fun fetchConfirmedCase(): Either<Throwable, ConfirmedCaseResponse> {
        return runCatching {
            value(service.getConfirmedCase())
        }.getOrElse { error ->
            failure(error)
        }
    }
}