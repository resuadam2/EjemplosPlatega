package com.resuadam2.curso.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VariosScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VariosScreenContent()
    }
}

@Composable
fun VariosScreenContent() {
    val contA = remember { mutableIntStateOf(0) }
    val contB = remember { mutableIntStateOf(0) }
    val contTotal = remember { mutableIntStateOf(0) }
    ContadorIndividual(
        contador = contA.intValue,
        onIncrementar = {
            contA.intValue++
            contTotal.intValue++
        },
        onReiniciar = {
            contA.intValue = 0
        }
    )
    ContadorIndividual(
        contador = contB.intValue,
        onIncrementar = {
            contB.intValue++
            contTotal.intValue++
        },
        onReiniciar = {
            contB.intValue = 0
        }
    )
    ContadorTotal(
        contador = contTotal.intValue,
        onReiniciar = {
            contTotal.intValue = 0
        }
    )
}

@Composable
fun ContadorIndividual(
    contador: Int,
    onIncrementar: () -> Unit,
    onReiniciar: () -> Unit,
) {
    Row {
        Button(
            onClick = onIncrementar
        ) {
            Text(text = "Incrementar")
        }
        Text(
            text = "Contador: $contador",
            modifier = Modifier.padding(16.dp)
        )
        IconButton(
            onClick = onReiniciar
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Reiniciar",
            )
        }
    }
}

@Composable
fun ContadorTotal(
    contador: Int,
    onReiniciar: () -> Unit
) {
    Row {
        Text(
            text = "Acumulado: $contador",
            modifier = Modifier.padding(16.dp)
        )
        IconButton(
            onClick = onReiniciar
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Reiniciar",
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun VariosScreenPreview() {
    VariosScreen()
}