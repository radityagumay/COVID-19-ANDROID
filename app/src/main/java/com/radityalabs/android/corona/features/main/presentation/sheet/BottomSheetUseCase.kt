package com.radityalabs.android.corona.features.main.presentation.sheet

import com.radityalabs.android.corona.features.main.presentation.uimodel.FeedModel

interface BottomSheetUseCase {
    suspend fun fetchFeeds(): List<FeedModel>
}

class BottomSheetUseCaseImpl(
    private val repository: BottomSheetRepository
) : BottomSheetUseCase {
    override suspend fun fetchFeeds(): List<FeedModel> {
        return repository.fetchFeeds()
    }
}