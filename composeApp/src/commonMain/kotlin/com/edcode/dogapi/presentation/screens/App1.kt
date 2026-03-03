package com.edcode.dogapi.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
@Preview
fun App1() {

    val viewModel = koinViewModel<DogApiViewModel>()

    MaterialTheme {
        var superheroName by remember { mutableStateOf("") }
        var superheroList by remember{ mutableStateOf<List<Hero>>(emptyList()) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                TextField(value = superheroName, onValueChange = { superheroName = it })
//                Button(onClick = { viewModel.getSuperheroList(superheroName) { superheroList = it } }) {
                    Text("Load")
                }
            }
            LazyColumn {
                items(superheroList){ hero ->
                    Text(hero.name)
                }
            }
        }
    }

