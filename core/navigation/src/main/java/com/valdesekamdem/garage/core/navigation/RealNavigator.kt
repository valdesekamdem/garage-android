package com.valdesekamdem.garage.core.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class RealNavigator : Navigator, NavigationEventSource {
    private val _events = MutableSharedFlow<NavigationEvent>(
        replay = 0,
        extraBufferCapacity = 1,
    )
    override val events: Flow<NavigationEvent> = _events.asSharedFlow()

    override fun goTo(screen: Screen) {
        check(_events.tryEmit(NavigationEvent.NavigateTo(screen))) {
            "Failed to enqueue navigation command: NavigateTo($screen)"
        }
    }

    override fun getBack() {
        check(_events.tryEmit(NavigationEvent.Back)) {
            "Failed to enqueue navigation command: Back"
        }
    }
}
