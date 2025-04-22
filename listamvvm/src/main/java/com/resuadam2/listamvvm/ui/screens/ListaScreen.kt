package com.resuadam2.listamvvm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.resuadam2.listamvvm.data.Tarea
import com.resuadam2.listamvvm.data.tareasExample

@Composable
fun ListaScreen() {
    ListaScreenContent()
}

@Composable
fun ListaScreenContent(tareas: List<Tarea> = emptyList()) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ListaForm()
        ListaList(tareas)
    }
}

@Composable
fun ListaList(
    tareas: List<Tarea> = emptyList(),
    expandirTarea: () -> Unit = {},
    eliminarTarea: () -> Unit = {},
    ) {
    LazyColumn {
        items(tareas) {
            TareaItem(
                tarea = it,
                expandirTarea = expandirTarea,
                eliminarTarea = eliminarTarea,
                )
        }
    }
}

@Composable
fun TareaItem(
    tarea: Tarea,
    expandirTarea: () -> Unit = {},
    eliminarTarea: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(16.dp)
                .clickable {
                    expandirTarea()
                }
            ,
        ) {
            Text(text = tarea.titulo,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium)
            Text(
                text = tarea.descripcion,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = if (tarea.expanded) Int.MAX_VALUE else 1,
                )
        }
        IconButton(
            onClick = eliminarTarea,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Delete",
            )
        }
    }

}

@Composable
fun ListaForm(
    titulo: String = "",
    descripcion: String = "",
    onTituloChange: (String) -> Unit = {},
    onDescripcionChange: (String) -> Unit = {},
    onAgregar: () -> Unit = {},
) {
    Column {
        TextField(
            value = titulo,
            onValueChange = {
                onTituloChange(it)
            },
            label = {Text("Titulo")},
        )
        TextField(
            value = descripcion,
            onValueChange = {
                onDescripcionChange(it)
            },
            label = { Text("Descripcion") },
        )
        Button(
            onClick = onAgregar,
        ) {
            Text("Agregar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListaScreenPreview() {
    ListaScreenContent(tareasExample)
}