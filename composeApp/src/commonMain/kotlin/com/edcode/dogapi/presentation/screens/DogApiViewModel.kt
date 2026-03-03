package com.edcode.dogapi.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edcode.dogapi.data.DogApiRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogApiViewModel(private val dogApiRepo: DogApiRepo): ViewModel() {


    private val _uiState = MutableStateFlow<LatestDogsUiState>(LatestDogsUiState.Loading(true))
    val uiState: StateFlow<LatestDogsUiState> = _uiState


    private fun getDogs() {
        viewModelScope.launch(Dispatchers.IO) {
            dogApiRepo.getDogs().collect {
                _uiState.value = LatestDogsUiState.Success(it)
            }
        }
    }

    private fun waitForLoading() {

        viewModelScope.launch {
            _uiState.value= LatestDogsUiState.Loading(true)
            delay(1000)
            _uiState.value= LatestDogsUiState.Loading(false)
        }
    }




    fun setEvent(event: LatestDogsUiEvent) {

        when (event) {
            is LatestDogsUiEvent.onClick -> {
                getDogs()
            }

            is LatestDogsUiEvent.onLoad -> {
                waitForLoading()
                getDogs()
            }
        }
    }
}

