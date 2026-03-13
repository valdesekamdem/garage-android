package com.valdesekamdem.garage.core.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationEventSource {
    val events: Flow<NavigationEvent>
}

sealed interface NavigationEvent {
    data class NavigateTo(val screen: Screen) : NavigationEvent

    data object Back : NavigationEvent
}
