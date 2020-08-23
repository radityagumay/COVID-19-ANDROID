package com.radityalabs.android.corona.features.main.presentation.sheet

import com.radityalabs.android.corona.features.main.presentation.uimodel.FeedModel
import com.radityalabs.android.corona.network.CovidService
import kotlinx.coroutines.delay
import javax.inject.Inject

interface BottomSheetRepository {
    suspend fun fetchFeeds(): List<FeedModel>
}

class BottomSheetRepositoryImpl @Inject constructor(
    private val service: CovidService
) : BottomSheetRepository {
    override suspend fun fetchFeeds(): List<FeedModel> {
        delay(1000)
        return dummyFeeds()
    }

    private fun dummyFeeds(): List<FeedModel> {
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
}