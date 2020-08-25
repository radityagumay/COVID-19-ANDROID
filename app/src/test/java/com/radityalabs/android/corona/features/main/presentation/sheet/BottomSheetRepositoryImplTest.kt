@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.radityalabs.android.corona.features.main.presentation.sheet

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.radityalabs.android.corona.CoroutineRule
import com.radityalabs.android.corona.features.main.presentation.uimodel.FeedModel
import com.radityalabs.android.corona.network.CovidService
import com.radityalabs.android.corona.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.net.HttpURLConnection

@Nested
@DisplayName("Given A Feed Service")
internal class BottomSheetRepositoryImplTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val service = mock<CovidService>()
    private lateinit var repository: BottomSheetRepository

    @BeforeEach
    fun setup() {
        repository = BottomSheetRepositoryImpl(service, true)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(service)
    }

    @Nested
    @DisplayName("When response code is ${HttpURLConnection.HTTP_OK}")
    inner class ResponseWith200 {

        private lateinit var feeds: List<FeedModel>

        @BeforeEach
        fun setup() = coroutineRule.runBlockingTest {
            whenever(
                service.getFeeds()
            ).thenReturn(generateRandomFeeds())

            feeds = repository.fetchFeeds()
        }

        @Test
        @DisplayName("Then verify feeds not empty")
        fun verifyFeedsNotEmpty() = coroutineRule.runBlockingTest {
            verify(service).getFeeds()
            assert(feeds.isNotEmpty())
        }
    }

    @Nested
    @DisplayName("When response code is ${HttpURLConnection.HTTP_NOT_FOUND}")
    inner class ResponseWith404 {

        private lateinit var feeds: List<FeedModel>

        @BeforeEach
        fun setup() = coroutineRule.runBlockingTest {
            whenever(
                service.getFeeds()
            ).thenReturn(emptyList())

            feeds = repository.fetchFeeds()
        }

        @Test
        @DisplayName("Then verify feeds is empty")
        fun verifyFeedsNotEmpty() = coroutineRule.runBlockingTest {
            verify(service).getFeeds()
            assert(feeds.isEmpty())
        }
    }
}

private fun generateRandomFeeds(): List<FeedModel> {
    val feeds = mutableListOf<FeedModel>()
    for (i in 0 until 100) {
        feeds.add(
            FeedModel(
                image = "http://www.gstatic.com/tv/thumb/persons/614/614_v9_bc.jpg",
                description = "Lorem ipsum, or lipsum as it is sometimes known, is dummy text used in laying out print, graphic or web designs. The passage is attributed to an unknown typesetter in the 15th century who is thought to have scrambled parts of Cicero's De Finibus Bonorum et Malorum for use in a type specimen book."
            )
        )
    }
    return feeds
}
