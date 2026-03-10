package com.valdesekamdem.garage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.valdesekamdem.garage.ui.theme.GarageTheme
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GarageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        onClick = {
                            goToGoogle()
                        }
                    )
                }
            }
        }
    }
}

private fun Activity.goToGoogle() {
    val intent = Intent(Intent.ACTION_VIEW)
//    intent.setDataAndType("http://www.google.com".toUri(), "MENI")
    intent.data = "https://www.google.com".toUri()
    startActivity(intent)
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(
            onClick = onClick,
        ) {
            Text("Open Google")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GarageTheme {
        Greeting("Android")
    }
}