package com.edcode.dogapi.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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

    LaunchedEffect(Unit)
    {
        viewModel.getDogs({superDogs=it})
    }

    MaterialTheme {
        Column(Modifier.fillMaxWidth().padding(top = 64.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(model =superDogs,null)

            Row {
                Button(onClick = { viewModel.getDogs({superDogs=it})  }) {
                    Text("Buscar")
                }
            }


          //  Text(superDogs)



        }
    }




   /* CoilImage(
        imageModel = { superDogs }, // loading a network image or local resource using an URL.
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    )*/
}


