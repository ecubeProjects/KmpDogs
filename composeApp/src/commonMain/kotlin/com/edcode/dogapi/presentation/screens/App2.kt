package com.edcode.dogapi.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App2() {

    val viewModel = koinViewModel<DogApiViewModel>()
    var superDogs by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    val state = viewModel.uiState


    viewModel.setEvent(event = LatestDogsUiEvent.onLoad)

    LaunchedEffect(state)
    {
        state.collect {
            when(it) {
                is LatestDogsUiState.Success -> {
                    superDogs = it.pic
                }
                is LatestDogsUiState.Error -> {

                }
                is LatestDogsUiState.Loading -> {
                    loading = it.check
                }
            }
        }
    }


    MaterialTheme {
        Column(Modifier.fillMaxWidth().padding(top = 64.dp), horizontalAlignment = Alignment.CenterHorizontally)
        {
            when(loading)
            {
                true -> CircularProgressIndicator()

                false ->    AsyncImage(model =superDogs,null)

            }

            Row {
                Button(onClick = {
                    viewModel.setEvent(event = LatestDogsUiEvent.onClick)
                    }) {
                    Text("Buscar")
        }
    }
        }
    }

}



sealed interface LatestDogsUiState {
    data class Success(val pic: String) : LatestDogsUiState
    data class Error(val exception: Throwable): LatestDogsUiState
    data class Loading(val check: Boolean): LatestDogsUiState
}

sealed class LatestDogsUiEvent {
     object  onClick: LatestDogsUiEvent()
      object onLoad: LatestDogsUiEvent()
 }


