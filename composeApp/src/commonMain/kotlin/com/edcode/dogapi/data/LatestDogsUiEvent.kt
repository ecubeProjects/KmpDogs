package com.edcode.dogapi.data

sealed class LatestDogsUiEvent {
      object OnClick: LatestDogsUiEvent()
      object OnLoading: LatestDogsUiEvent()
 }
