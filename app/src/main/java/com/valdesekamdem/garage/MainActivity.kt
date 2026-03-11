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
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.valdesekamdem.garage.ui.theme.GarageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GarageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Main(
    modifier: Modifier = Modifier
) {
    val backStack = remember { mutableStateListOf<Screen>(HomeScreen) }

    val entryProvider = entryProvider {
        entry<HomeScreen> {
            Home(
                onUserClick = { backStack.add(UserDetailScreen(it)) }
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
