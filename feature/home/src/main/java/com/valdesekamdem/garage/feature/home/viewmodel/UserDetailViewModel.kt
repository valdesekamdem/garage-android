package com.valdesekamdem.garage.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import com.valdesekamdem.garage.core.navigation.api.Navigator
import com.valdesekamdem.garage.core.presentation.StateHolder
import com.valdesekamdem.garage.feature.home.screens.UserDetailScreen
import com.valdesekamdem.garage.feature.home.viewmodel.UserDetailUiEvent.GoBack
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed interface UserDetailUiEvent {
    data object GoBack: UserDetailUiEvent
}

data class UserDetailUiState(
    val name: String,
)

@HiltViewModel(assistedFactory = UserDetailViewModel.Factory::class)
class UserDetailViewModel @AssistedInject constructor(
    private val navigator: Navigator,
    @Assisted screen: UserDetailScreen,
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

    @AssistedFactory
    fun interface Factory {
        fun create(screen: UserDetailScreen): UserDetailViewModel
    }
}