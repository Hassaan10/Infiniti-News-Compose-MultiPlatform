package org.example.infinitinews.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.infinitinews.data.network.ApiResponse
import org.example.infinitinews.domain.interactor.HeadlinesUseCase
import org.example.infinitinews.ui.events.UIEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.infinitinews.domain.interactor.AllNewsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel: ViewModel(), KoinComponent {

    private val allNewsUseCase: AllNewsUseCase by inject()
    private val headlineUseCase: HeadlinesUseCase by inject()

    private val _allNewsState = MutableStateFlow<ApiResponse>(ApiResponse.Loading)
    val allNewsState = _allNewsState.asStateFlow()

    private val _headlinesState = MutableStateFlow<ApiResponse>(ApiResponse.Loading)
    val headlinesState = _headlinesState.asStateFlow()

    init {
        fetchHeadLines()
        fetchAllNews()
    }

    private fun fetchAllNews() {
        viewModelScope.launch {
            allNewsUseCase.invoke().collect {
                _allNewsState.value = it
            }
        }
    }

    private fun fetchHeadLines() {
        viewModelScope.launch {
            headlineUseCase.invoke().collect {
                _headlinesState.value = it
            }
        }
    }

    fun sendUIEvents(uiEvent: UIEvent) {
        when(uiEvent) {
            UIEvent.FetchAllNews -> fetchAllNews()
            UIEvent.FetchEverything -> {
                fetchHeadLines()
                fetchAllNews()
            }
            UIEvent.FetchHeadlines -> fetchHeadLines()
        }
    }
}