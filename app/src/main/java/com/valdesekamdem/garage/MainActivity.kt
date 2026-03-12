package com.valdesekamdem.garage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.valdesekamdem.garage.home.Home
import com.valdesekamdem.garage.home.HomeViewModel
import com.valdesekamdem.garage.ui.theme.GarageTheme

class MainActivity : ComponentActivity() {
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        homeViewModel = HomeViewModel()

        setContent {
            GarageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        homeViewModel = homeViewModel,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Main(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val backStack = remember { mutableStateListOf<Screen>(HomeScreen) }

    val entryProvider = entryProvider {
        entry<HomeScreen> {
            Home(
                uiState = homeViewModel.uiState.collectAsStateWithLifecycle().value,
//                onUserClick = { backStack.add(UserDetailScreen(it)) }
            )
        }
        entry<UserDetailScreen> { screen ->
            UserDetail(
                name = screen.name,
                onBackClick = { backStack.removeLastOrNull() }
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider,
        modifier = modifier,
    )
}
