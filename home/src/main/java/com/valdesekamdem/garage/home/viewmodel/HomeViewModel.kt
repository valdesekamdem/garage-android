package com.valdesekamdem.garage.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valdesekamdem.garage.core.navigation.Navigator
import com.valdesekamdem.garage.home.screens.UserDetailScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val navigator: Navigator,
) : ViewModel() {

    val users = listOf(
        "Valdese Kamdem",
        "Jean Dark",
        "Clark Monday",
        "Just Oliver"
    )

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when(event) {
            is HomeUiEvent.SelectUser -> {
                Log.d("TAG", "${event.selectedUser} selected. Navigate to the view details")
                navigator.goTo(UserDetailScreen(event.selectedUser))
            }
        }
    }

    val uiState: StateFlow<HomeUiState>
        field = MutableStateFlow(
            HomeUiState(
                users = users,
                onEvent = onEvent,
            )
        )
}