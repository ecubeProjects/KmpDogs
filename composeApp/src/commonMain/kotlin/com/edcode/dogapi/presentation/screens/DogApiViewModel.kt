package com.edcode.dogapi.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edcode.dogapi.data.DogApiRepo
import com.edcode.dogapi.models.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class DogApiViewModel(private val dogApiRepo: DogApiRepo): ViewModel() {

    fun getSuperheroList(hero: String, onSuccessResponse: (List<Hero>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            dogApiRepo.getSuperheroList(hero) {
                onSuccessResponse(it)
            }
        }
    }

    fun getDogs(onSuccessResponse: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            dogApiRepo.getDogs {
                onSuccessResponse(it)
            }
        }
    }
}

