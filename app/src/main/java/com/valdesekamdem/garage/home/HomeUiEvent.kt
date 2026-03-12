package com.valdesekamdem.garage.home

sealed interface HomeUiEvent {
    data class SelectUser(val selectedUser: String): HomeUiEvent
}