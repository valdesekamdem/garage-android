package com.valdesekamdem.garage.home.viewmodel

import androidx.lifecycle.ViewModel
import com.valdesekamdem.garage.core.navigation.Navigator
import com.valdesekamdem.garage.home.screens.UserDetailScreen
import com.valdesekamdem.garage.home.viewmodel.UserDetailUiEvent.GoBack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed interface UserDetailUiEvent {
    data object GoBack: UserDetailUiEvent
}

data class UserDetailUiState(
    val name: String,
    val onEvent: (UserDetailUiEvent) -> Unit,
)

class UserDetailViewModel(
    private val navigator: Navigator,
    screen: UserDetailScreen,
) : ViewModel() {

    val onEvent: (UserDetailUiEvent) -> Unit = { event ->
        when(event) {
            is GoBack -> {
                navigator.getBack()
            }
        }
    }

    val uiState: StateFlow<UserDetailUiState>
        field = MutableStateFlow(
            UserDetailUiState(
                name = screen.name,
                onEvent = onEvent,
            )
        )
}