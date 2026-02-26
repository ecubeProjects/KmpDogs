package com.edcode.dogapi.data

import com.edcode.dogapi.models.Hero


interface DogApiRepo {
    fun getSuperheroList(superheroName: String, onSuccessResponse: (List<Hero>) -> Unit)
}

