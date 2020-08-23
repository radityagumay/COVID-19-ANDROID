package com.radityalabs.android.corona.features.main.presentation.sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radityalabs.android.corona.features.main.presentation.uimodel.FeedModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor(
    private val useCase: BottomSheetUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _feeds = MutableLiveData<List<FeedModel>>()
    val feeds: LiveData<List<FeedModel>> get() = _feeds

    fun fetchFeeds() {
        viewModelScope.launch(dispatcher) {
            _feeds.postValue(useCase.fetchFeeds())
        }
    }
}