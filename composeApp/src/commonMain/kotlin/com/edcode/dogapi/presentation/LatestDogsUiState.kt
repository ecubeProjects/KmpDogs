package com.edcode.dogapi.presentation

sealed interface LatestDogsUiState {
    data class Success(val pic: String) : LatestDogsUiState
    data class Error(val exception: Throwable): LatestDogsUiState
    data class Loading(val check: Boolean): LatestDogsUiState
}