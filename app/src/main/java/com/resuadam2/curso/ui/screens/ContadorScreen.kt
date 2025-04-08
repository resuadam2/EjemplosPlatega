package com.resuadam2.curso.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContadorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Contador()
    }
}
/*
@Composable
fun Contador() {
    val contador = rememberSaveable { mutableIntStateOf(0) }
    // var contador = 0 Así no recompone el Composable
    val doble = remember { derivedStateOf { contador.intValue * 2 }}

    Button(onClick = {
        contador.intValue++
        Log.d("Contador", "Contador: ${contador.intValue}")
    }) {
        Text(text = "Contador: ${contador.intValue}")
    }
    Text("Doble: ${doble.value}")
}

 */

@Composable
fun Contador() {
    val contador = remember { mutableIntStateOf(0) }
    ContadorBoton {
        contador.intValue++
        Log.d("Contador", "Contador: ${contador.intValue}")
    }
    ContadorTexto(contador)
}

@Composable
fun ContadorBoton(incrementar: () -> Unit) {
    Button(onClick = incrementar) {
        Text(text = "Incrementar")
    }
}

@Composable
fun ContadorTexto(contador: MutableState<Int>) {
    Text(text = "Contador: ${contador.value}")
}

@Preview
@Composable
fun ContadorScreenPreview() {
    ContadorScreen()
}