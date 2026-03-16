package com.valdesekamdem.garage.home

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.valdesekamdem.garage.core.navigation.Navigator
import com.valdesekamdem.garage.core.presentation.BindScreen
import com.valdesekamdem.garage.core.presentation.UiFactory
import com.valdesekamdem.garage.home.screens.HomeScreen
import com.valdesekamdem.garage.home.screens.UserDetailScreen
import com.valdesekamdem.garage.home.ui.Home
import com.valdesekamdem.garage.home.ui.UserDetail
import com.valdesekamdem.garage.home.viewmodel.HomeViewModel
import com.valdesekamdem.garage.home.viewmodel.UserDetailViewModel
import javax.inject.Inject

class HomeUiFactory @Inject constructor(
    private val navigator: Navigator,
): UiFactory {

    override fun register(scope: EntryProviderScope<NavKey>) = with(scope) {
        entry<HomeScreen> { screen ->
            BindScreen(
                screen = screen,
                stateHolderFactory = { HomeViewModel(navigator) }
            ) { uiState, onEvent ->
                Home(
                    uiState = uiState,
                    onEvent = onEvent,
                )
            }
        }
        entry<UserDetailScreen> { screen ->
            BindScreen(
                screen = screen,
                stateHolderFactory = { UserDetailViewModel(navigator, screen) }
            ) { uiState, onEvent ->
                UserDetail(
                    uiState = uiState,
                    onEvent = onEvent,
                )
            }
        }
    }
}
