package com.radityalabs.android.corona.network

import com.radityalabs.android.corona.network.model.response.ConfirmedCaseResponse
import retrofit2.http.GET

interface CovidService {
    @GET(CONFIRMED_CASE)
    suspend fun getConfirmedCase(): ConfirmedCaseResponse
}