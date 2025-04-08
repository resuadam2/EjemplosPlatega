package com.resuadam2.curso.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

val names = listOf("Android", "Compose", "Jetpack", "Kotlin", "Studio")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Curso de Compose")
                },
            )
        }
    ) { paddingValues ->
        GreetingList(names = names, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun GreetingList(names: List<String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        names.forEach {
            Greeting(name = it)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(32.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.tertiary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            "Hello $name!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.size(32.dp, 0.dp))
        Icon(
            Icons.Default.CheckCircle,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = "icono de ejemplo",
            modifier = Modifier.clickable {
                // Lo que quiero que haga al hacer click en el
            }
        )
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = "Click me",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

/**
 * Preview for dark mode
 */
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun MainScreenPreviewDark() {
    MainScreen()
}