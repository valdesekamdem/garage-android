package com.valdesekamdem.garage.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valdesekamdem.garage.core.navigation.api.Navigator
import com.valdesekamdem.garage.core.presentation.StateHolder
import com.valdesekamdem.garage.feature.home.screens.UserDetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel(), StateHolder<HomeUiState, HomeUiEvent> {

    val users = listOf(
        "Valdese Kamdem",
        "Jean Dark",
        "Clark Monday",
        "Just Oliver"
    )

    override fun onUiEvent(event: HomeUiEvent) {
        when(event) {
            is HomeUiEvent.SelectUser -> {
                Log.d("TAG", "${event.selectedUser} selected. Navigate to the view details")
                navigator.goTo(UserDetailScreen(event.selectedUser))
            }
        }
    }

    private val _uiState = MutableStateFlow(
        HomeUiState(
            users = users,
        )
    )
    override val uiState: StateFlow<HomeUiState> = _uiState
}