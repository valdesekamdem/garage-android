package com.valdesekamdem.garage

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

val users = listOf(
    "Valdese Kamdem",
    "Jean Dark",
    "Clark Monday",
    "Just Oliver"
)

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onUserClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
    ) {
        items(items = users, key = { it.hashCode() }) { name ->
            UserCell(
                name = name,
                onClick = { onUserClick(name) }
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
    Home()
}
