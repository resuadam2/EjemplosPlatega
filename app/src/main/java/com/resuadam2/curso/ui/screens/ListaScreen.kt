package com.resuadam2.curso.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Elemento(
    val nombre: String,
    val descripcion: String,
)

private val elementos = listOf(
    Elemento("Elemento 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
    Elemento("Elemento 2", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
    Elemento("Elemento 3", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
    Elemento("Elemento 4", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."),
    Elemento("Elemento 5", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
    Elemento("Elemento 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
    Elemento("Elemento 7", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
    Elemento("Elemento 8", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
    Elemento("Elemento 9", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."),
    Elemento("Elemento 10", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
    Elemento("Elemento 11", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
    Elemento("Elemento 12", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
)

@Composable
fun ListaScreen() {
    /*
        Column() {
        elementos.forEach { elemento ->
            ElementoItem(elemento)
        }
        }
         */
    LazyColumn {
        /*
        items(elementos.size) { posicion ->
            ElementoItem(elementos[posicion])
        }
         */
        items(elementos) { elem ->
            ElementoItem(elem)
        }
    }
}

@Composable
fun ElementoItem(elemento: Elemento) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        CustomText(text = elemento.nombre)
        CustomText(text = elemento.descripcion)
    }
}

@Composable
fun CustomText(text: String) {
    var expanded by remember { mutableStateOf(false) }
    // var expanded = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable {
                expanded = !expanded
            }
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = if (expanded) Int.MAX_VALUE else 1, // Cambia a 1 para mostrar solo una línea
        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun ListaScreenPreview() {
    ListaScreen()
}