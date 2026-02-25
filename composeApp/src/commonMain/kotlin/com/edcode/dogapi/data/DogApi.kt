package com.edcode.dogapi.data

import com.edcode.dogapi.di.Hero

interface DogApiRepo {
    fun getSuperheroList(superheroName: String, onSuccessResponse: (List<Hero>) -> Unit)
}

