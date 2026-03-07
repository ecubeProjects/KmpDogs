package com.edcode.dogapi.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edcode.dogapi.di.DogUseCases
import com.edcode.dogapi.presentation.LatestDogsUiEvent
import com.edcode.dogapi.presentation.LatestDogsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DogApiViewModel(private val dogApiUseCases: DogUseCases): ViewModel() {


    private val _uiState = MutableStateFlow<LatestDogsUiState>(LatestDogsUiState.Loading(true))
    val uiState: StateFlow<LatestDogsUiState> = _uiState


    init {
        getBreads()
       setEvent(event = LatestDogsUiEvent.OnLoading)
    }

    private fun getDogs() {
        viewModelScope.launch(Dispatchers.IO) {
            dogApiUseCases.GetSingleDogPics().collect{ /*Venimos de un flow de ahi el collect*/
                _uiState.value = LatestDogsUiState.Success(it)
            }
        }
    }

    private fun waitForLoading() {

        viewModelScope.launch {
            _uiState.update {
                   LatestDogsUiState.Loading(true)
            }
            delay(1000)
            _uiState.update {
                LatestDogsUiState.Loading(false)
            }

        }
    }

    private fun getBreads() {
        viewModelScope.launch(Dispatchers.IO) {
            dogApiUseCases.getBreads().collect {
                println("Razas = ${it.keys}")
            }
        }
    }







    fun setEvent(event: LatestDogsUiEvent) {

        when (event) {
            is LatestDogsUiEvent.OnClick -> {
                getDogs()
            }

            is LatestDogsUiEvent.OnLoading -> {
                waitForLoading()
                getDogs()
            }


        }
    }
}



