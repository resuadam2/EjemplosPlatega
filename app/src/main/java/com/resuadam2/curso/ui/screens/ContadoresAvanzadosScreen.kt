package com.resuadam2.curso.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContadoresAvanzadosScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ContadoresAvanzadosScreenContent()
    }
}

@Composable
fun ContadoresAvanzadosScreenContent() {
    val contA = remember { mutableIntStateOf(0) }
    val incrementoA = remember { mutableStateOf("1") }
    val contB = remember { mutableIntStateOf(0) }
    val incrementoB = remember { mutableStateOf("1") }
    val contTotal = remember { mutableIntStateOf(0) }

    ContadorIndividualAvanzado(
        contador = contA.intValue,
        incremento = incrementoA.value,
        onIncrementoChange = {
            incrementoA.value = it
        },
        onTextFieldClicked = {
            incrementoA.value = ""
        },
        onIncrementar = {
            contA.intValue += incrementoA.value.toIntOrNull() ?: 0
            contTotal.intValue += incrementoA.value.toIntOrNull() ?: 0
        },
        onReiniciar = {
            contA.intValue = 0
        }
    )
    Spacer(modifier = Modifier.size(32.dp))
    ContadorIndividualAvanzado(
        contador = contB.intValue,
        incremento = incrementoB.value,
        onIncrementoChange = {
            incrementoB.value = it
        },
        onTextFieldClicked = {
            incrementoB.value = ""
        },
        onIncrementar = {
            contB.intValue += incrementoB.value.toIntOrNull() ?: 0
            contTotal.intValue += incrementoB.value.toIntOrNull() ?: 0
        },
        onReiniciar = {
            contB.intValue = 0
        }
    )
    Spacer(modifier = Modifier.size(32.dp))
    ContadorTotal(
        contador = contTotal.intValue,
        onReiniciar = {
            contTotal.intValue = 0
        }
    )
}


@Composable
fun ContadorIndividualAvanzado(
    contador: Int,
    incremento: String,
    onIncrementoChange: (String) -> Unit,
    onTextFieldClicked: () -> Unit,
    onIncrementar: () -> Unit,
    onReiniciar: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Column {
        TextField(
            value = incremento,
            onValueChange = onIncrementoChange,
            label = { Text(text = "Incremento") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .onFocusChanged {
                    if (it.hasFocus) {
                        onTextFieldClicked()
                    } else if (incremento.isBlank()) onIncrementoChange("1")
                }
        )
        Row {
            Button(
                onClick = {
                    try {
                        if(incremento.isBlank() || incremento.toInt() <= 0) {
                            Toast.makeText(context,
                                "El incremento debe ser un número positivo",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            onIncrementar()
                        }
                    } catch (e: NumberFormatException) {
                        Toast.makeText(context,
                            "El incremento debe ser un número positivo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    focusManager.clearFocus()
                }
            ) {
                Text(text = "Incrementar")
            }
            Text(
                text = "Contador: $contador",
                modifier = Modifier.padding(16.dp)
            )
            IconButton(
                onClick = {
                    onReiniciar()
                    focusManager.clearFocus()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Reiniciar",
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContadoresAvanzadosScreenPreview() {
    ContadoresAvanzadosScreen()
}