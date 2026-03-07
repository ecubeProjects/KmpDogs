package com.edcode.dogapi.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.edcode.dogapi.presentation.LatestDogsUiEvent
import com.edcode.dogapi.presentation.LatestDogsUiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App2() {
    val viewModel = koinViewModel<DogApiViewModel>()
    val state by viewModel.uiState.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val currentState = state) {
                is LatestDogsUiState.Loading -> {
                    if (currentState.check) {
                        CircularProgressIndicator()
                        Spacer(Modifier.height(8.dp))
                        Text("Cargando...")
                    }
                }
                is LatestDogsUiState.Success -> {
                    AsyncImage(
                        model = currentState.pic,
                        contentDescription = "Perro",
                        modifier = Modifier.size(300.dp)
                    )
                }
                is LatestDogsUiState.Error -> {
                    Text("Error", color = MaterialTheme.colorScheme.error)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            MyDropdownMenu { selection -> println(selection) }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.setEvent(LatestDogsUiEvent.OnClick) }) {
                Text("Buscar Nuevo")
            }
        }
    }
}

@Composable
fun MyDropdownMenu(onItemClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Opción 1", "Opción 2")
    var selectedItem by remember { mutableStateOf(items[0]) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedItem = item
                        expanded = false
                        onItemClick(item)
                    }
                )
            }
        }
    }
}
