package com.example.recipe.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.common.coroutines.di.DispatchersIo
import com.example.recipe.domain.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val useCase: GetRecipesUseCase,
        @DispatchersIo private val dispatcher: CoroutineDispatcher,
    ) : ViewModel() {

        private var _uiState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
        val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

        fun getRecipeList() {
            useCase()
                .onStart { _uiState.value = _uiState.value.showLoading() }
                .onEach { _uiState.value = _uiState.value.updateRecipeList(it) }
                .onCompletion { _uiState.value = _uiState.value.hideLoading() }
                .catch { /* todo: to implement */ }
                .flowOn(dispatcher)
                .launchIn(viewModelScope)
        }

        fun updateFilter(text: String) {
            _uiState.value = _uiState.value.updateFilter(text)
        }
    }
