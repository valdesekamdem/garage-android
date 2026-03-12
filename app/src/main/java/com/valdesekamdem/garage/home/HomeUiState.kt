package com.valdesekamdem.garage.home

data class HomeUiState(
    val users: List<String>,
    val onEvent: (HomeUiEvent) -> Unit,
)