package com.edcode.dogapi.presentation

sealed class LatestDogsUiEvent {
      object OnClick: LatestDogsUiEvent()
      object OnLoading: LatestDogsUiEvent()

 }
