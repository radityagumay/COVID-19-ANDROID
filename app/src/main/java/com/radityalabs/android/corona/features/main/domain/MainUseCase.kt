package com.radityalabs.android.corona.features.main.domain

import com.radityalabs.android.corona.di.Injector
import com.radityalabs.android.corona.features.main.data.MainRepository
import com.radityalabs.android.corona.network.model.response.ConfirmedCaseResponse

interface MainUseCase {
    fun fetchConfirmedCovid(): List<ConfirmedCaseResponse>
}

class MainUseCaseImpl(
    private val repository: MainRepository = Injector.get()
) : MainUseCase {
    override fun fetchConfirmedCovid(): List<ConfirmedCaseResponse> {
        TODO("Not yet implemented")
    }
}