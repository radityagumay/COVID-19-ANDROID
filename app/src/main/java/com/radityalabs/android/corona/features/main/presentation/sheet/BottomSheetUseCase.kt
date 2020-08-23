package com.radityalabs.android.corona.features.main.presentation.sheet

import com.radityalabs.android.corona.features.main.presentation.uimodel.FeedModel
import javax.inject.Inject

interface BottomSheetUseCase {
    suspend fun fetchFeeds(): List<FeedModel>
}

class BottomSheetUseCaseImpl @Inject constructor(
    private val repository: BottomSheetRepository
) : BottomSheetUseCase {
    override suspend fun fetchFeeds(): List<FeedModel> {
        return repository.fetchFeeds()
    }
}