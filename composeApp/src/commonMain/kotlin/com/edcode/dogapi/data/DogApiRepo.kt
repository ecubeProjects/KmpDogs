package com.edcode.dogapi.data

import com.edcode.dogapi.models.Hero
import kotlinx.coroutines.flow.Flow


interface DogApiRepo {
    fun getSuperheroList(superheroName: String, onSuccessResponse: (List<Hero>) -> Unit)
    suspend fun getDogs(): Flow<String>
    suspend fun getSingleDogPics(raza:String): Flow<String>
    suspend fun getBreads(): Flow< Map<String, List<String>>>
}

