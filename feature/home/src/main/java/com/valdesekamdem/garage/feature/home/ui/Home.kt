package com.valdesekamdem.garage.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valdesekamdem.garage.feature.home.viewmodel.HomeUiEvent
import com.valdesekamdem.garage.feature.home.viewmodel.HomeUiEvent.SelectUser
import com.valdesekamdem.garage.feature.home.viewmodel.HomeUiState

@Composable
fun Home(
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
    ) {
        items(items = uiState.users, key = { it.hashCode() }) { name ->
            UserCell(
                name = name,
                onClick = { onEvent(SelectUser(name)) }
            )
        }
    }
}

@Composable
fun UserCell(
    name: String,
    onClick: () -> Unit = {},
) {
    Text(
        text = name,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .clickable(true) { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(
        uiState = HomeUiState(
            users = listOf(
                "Valdese Kamdem",
                "Jean Dark",
                "Clark Monday",
                "Just Oliver"
            ),
        ),
        onEvent = {}
    )
}
