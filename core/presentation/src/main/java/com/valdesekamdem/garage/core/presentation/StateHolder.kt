package com.valdesekamdem.garage.core.presentation

import kotlinx.coroutines.flow.StateFlow

interface StateHolder<UiState, UiEvent> {
    val uiState: StateFlow<UiState>

    fun onUiEvent(event: UiEvent)
}