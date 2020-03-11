package com.radityalabs.android.corona.features.main.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.radityalabs.android.corona.CoroutineRule
import com.radityalabs.android.corona.helpers.fromJson
import com.radityalabs.android.corona.helpers.value
import com.radityalabs.android.corona.network.CovidService
import com.radityalabs.android.corona.network.response.ConfirmedCaseResponse
import com.radityalabs.android.corona.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryImplTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val service = mock<CovidService>()
    private val repository = MainRepositoryImpl(service)

    @Test
    fun fetchConfirmedCase() {
        coroutineRule.runBlockingTest {
            val expected = fromJson<ConfirmedCaseResponse>("response_confirmed_case.json")

            whenever(
                service.getConfirmedCase()
            ).thenReturn(expected)

            assertEquals(expected, repository.fetchConfirmedCase().value())
        }
    }
}