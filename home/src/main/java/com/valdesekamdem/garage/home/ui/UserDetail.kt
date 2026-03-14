package com.valdesekamdem.garage.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valdesekamdem.garage.home.viewmodel.UserDetailUiEvent
import com.valdesekamdem.garage.home.viewmodel.UserDetailUiState

@Composable
fun UserDetail(
    uiState: UserDetailUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Hello, ${uiState.name}!",
            modifier = modifier,
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.padding(16.dp))

        TextButton(
            onClick = { uiState.onEvent(UserDetailUiEvent.GoBack) },
        ) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailPreview() {
    UserDetail(UserDetailUiState(name = "John Snow", onEvent = {}))
}