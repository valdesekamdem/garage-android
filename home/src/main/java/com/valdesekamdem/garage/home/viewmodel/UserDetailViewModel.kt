package com.valdesekamdem.garage.home.viewmodel

import androidx.lifecycle.ViewModel
import com.valdesekamdem.garage.core.navigation.Navigator
import com.valdesekamdem.garage.core.presentation.StateHolder
import com.valdesekamdem.garage.home.screens.UserDetailScreen
import com.valdesekamdem.garage.home.viewmodel.UserDetailUiEvent.GoBack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed interface UserDetailUiEvent {
    data object GoBack: UserDetailUiEvent
}

data class UserDetailUiState(
    val name: String,
)

class UserDetailViewModel(
    private val navigator: Navigator,
    screen: UserDetailScreen,
) : ViewModel(), StateHolder<UserDetailUiState, UserDetailUiEvent> {

    override fun onUiEvent(event: UserDetailUiEvent) {
        when(event) {
            is GoBack -> {
                navigator.getBack()
            }
        }
    }

    private val _uiState = MutableStateFlow(
        UserDetailUiState(
            name = screen.name,
        )
    )
    override val uiState: StateFlow<UserDetailUiState> = _uiState
}