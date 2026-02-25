package com.edcode.dogapi.di

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
import org.koin.dsl.module
import com.edcode.dogapi.data.DogApiRepo
import com.edcode.dogapi.di.NetworkUtils.httpClient
import com.edcode.dogapi.domain.DogApiRepoImp
import com.edcode.dogapi.presentation.screens.DogApiViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin


val appModule = module{

   single<DogApiRepo> { DogApiRepoImp() }
   factory { DogApiViewModel(dogApiRepo = get()) }

}



fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}

object NetworkUtils {
    val httpClient = HttpClient {
        install(ContentNegotiation){
            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
    }
}

@Serializable
data class ApiResponse(
    val results:List<Hero>,
    @SerialName("response")
    val ok:String
)

@Serializable
data class Hero(
    val id:String,
    val name:String
)

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
                Button(onClick = { viewModel.getSuperheroList(superheroName) { superheroList = it } }) {
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
}
