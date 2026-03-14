package com.valdesekamdem.garage.home.viewmodel

sealed interface HomeUiEvent {
    data class SelectUser(val selectedUser: String): HomeUiEvent
}