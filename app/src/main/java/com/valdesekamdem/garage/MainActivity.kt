package com.valdesekamdem.garage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.valdesekamdem.garage.core.navigation.NavigationEvent
import com.valdesekamdem.garage.core.navigation.NavigationEventSource
import com.valdesekamdem.garage.core.navigation.Navigator
import com.valdesekamdem.garage.core.presentation.UiFactoryRegistry
import com.valdesekamdem.garage.home.screens.HomeScreen
import com.valdesekamdem.garage.ui.theme.GarageTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var navigationEventSource: NavigationEventSource

    @Inject
    lateinit var uiFactoryRegistry: UiFactoryRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val backStack = rememberNavBackStack(HomeScreen)
            BindNavigator(navigationEventSource = navigationEventSource, backStack = backStack)

            GarageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        backStack = backStack,
                        navigator = navigator,
                        uiFactoryRegistry = uiFactoryRegistry,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun BindNavigator(
    navigationEventSource: NavigationEventSource,
    backStack: NavBackStack<NavKey>,
) {
    LaunchedEffect(navigationEventSource, backStack) {
        navigationEventSource.events.collect { navigationEvent ->
            when (navigationEvent) {
                NavigationEvent.Back -> backStack.removeLastOrNull()
                is NavigationEvent.NavigateTo -> backStack.add(navigationEvent.screen)
            }
        }
    }
}

@Composable
fun Main(
    backStack: NavBackStack<NavKey>,
    navigator: Navigator,
    uiFactoryRegistry: UiFactoryRegistry,
    modifier: Modifier = Modifier
) {
    NavDisplay(
        backStack = backStack,
        onBack = { navigator.getBack() },
        entryProvider = uiFactoryRegistry.entryProvider,
        modifier = modifier,
    )
}
