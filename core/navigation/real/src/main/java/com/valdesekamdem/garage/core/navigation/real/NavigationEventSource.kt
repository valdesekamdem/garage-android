package com.valdesekamdem.garage.core.navigation.real

import com.valdesekamdem.garage.core.navigation.api.Screen
import kotlinx.coroutines.flow.Flow

interface NavigationEventSource {
    val events: Flow<NavigationEvent>
}

sealed interface NavigationEvent {
    data class NavigateTo(val screen: Screen) : NavigationEvent

    data object Back : NavigationEvent
}
