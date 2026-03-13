package com.valdesekamdem.garage.core.navigation

data class NavigatorBindings(
    val navigator: Navigator,
    val eventSource: NavigationEventSource,
)

fun createNavigatorBindings(): NavigatorBindings {
    val realNavigator = RealNavigator()
    return NavigatorBindings(
        navigator = realNavigator,
        eventSource = realNavigator,
    )
}
