package com.valdesekamdem.garage.feature.home.viewmodel

sealed interface HomeUiEvent {
    data class SelectUser(val selectedUser: String): HomeUiEvent
}