package com.radityalabs.android.corona.features.main.domain

import com.radityalabs.android.corona.features.main.data.MainRepository
import com.radityalabs.android.corona.network.model.response.ConfirmedCaseResponse
import javax.inject.Inject

interface MainUseCase {
    fun fetchConfirmedCovid(): List<ConfirmedCaseResponse>
}

class MainUseCaseImpl @Inject constructor(
    private val repository: MainRepository
) : MainUseCase {
    override fun fetchConfirmedCovid(): List<ConfirmedCaseResponse> {
        TODO("Not yet implemented")
    }
}