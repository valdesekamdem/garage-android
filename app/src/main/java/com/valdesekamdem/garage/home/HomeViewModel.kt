package com.valdesekamdem.garage.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valdesekamdem.garage.home.HomeUiEvent.SelectUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    val users = listOf(
        "Valdese Kamdem",
        "Jean Dark",
        "Clark Monday",
        "Just Oliver"
    )

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when(event) {
            is SelectUser -> {
                Log.d("TAG", "${event.selectedUser} selected. Navigate to the view details")
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