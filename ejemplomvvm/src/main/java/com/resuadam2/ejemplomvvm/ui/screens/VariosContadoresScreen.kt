package com.resuadam2.ejemplomvvm.ui.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.resuadam2.ejemplomvvm.ui.state.VariosContadoresViewModel

@Composable
fun VariosContadoresScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VariosScreenContent()
    }
}

@Composable
fun VariosScreenContent(
    variosContadoresVM: VariosContadoresViewModel = viewModel()
) {
    val state by variosContadoresVM.uiState.collectAsState()

    ContadorIndividual(
        contador = state.contadorA,
        onIncrementar = {
            variosContadoresVM.incrementarA()
        },
        onReiniciar = {
            variosContadoresVM.reiniciarA()
        }
    )
    ContadorIndividual(
        contador = state.contadorB,
        onIncrementar = {
            variosContadoresVM.incrementarB()
        },
        onReiniciar = {
            variosContadoresVM.reiniciarB()
        }
    )
    ContadorTotal(
        contador = state.contadorTotal,
        onReiniciar = {
            variosContadoresVM.reiniciarTotal()
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
    VariosContadoresScreen()
}