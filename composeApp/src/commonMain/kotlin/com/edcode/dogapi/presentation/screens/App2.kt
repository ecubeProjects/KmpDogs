package com.edcode.dogapi.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.edcode.dogapi.models.Hero
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.emptyList

@Composable
@Preview
fun App2() {

    val viewModel = koinViewModel<DogApiViewModel>()
    var superDogs by remember { mutableStateOf("") }


    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Button(onClick = { viewModel.getDogs({superDogs=it})  }) {
                    Text("Buscar")
                }
            }

            Text(superDogs)
        }
    }
}
