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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.valdesekamdem.garage.core.navigation.NavigationEvent
import com.valdesekamdem.garage.core.navigation.NavigationEventSource
import com.valdesekamdem.garage.core.navigation.Navigator
import com.valdesekamdem.garage.core.navigation.createNavigatorBindings
import com.valdesekamdem.garage.home.Home
import com.valdesekamdem.garage.home.HomeViewModel
import com.valdesekamdem.garage.ui.theme.GarageTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val navigatorBindings = createNavigatorBindings()
        val navigator = navigatorBindings.navigator
        val navigationEventSource = navigatorBindings.eventSource
        val homeViewModel = HomeViewModel(navigator)

        setContent {
            val backStack = rememberNavBackStack(HomeScreen)
            BindNavigator(navigationEventSource = navigationEventSource, backStack = backStack)

            GarageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        backStack = backStack,
                        navigator = navigator,
                        homeViewModel = homeViewModel,
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
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val entryProvider: (NavKey) -> NavEntry<NavKey> = entryProvider {
        entry<HomeScreen> {
            Home(
                uiState = homeViewModel.uiState.collectAsStateWithLifecycle().value,
            )
        }
        entry<UserDetailScreen> { screen ->
            UserDetail(
                name = screen.name,
                onBackClick = { navigator.getBack() }
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { navigator.getBack() },
        entryProvider = entryProvider,
        modifier = modifier,
    )
}
