package com.valdesekamdem.garage.home.viewmodel

data class HomeUiState(
    val users: List<String>,
    val onEvent: (HomeUiEvent) -> Unit,
)