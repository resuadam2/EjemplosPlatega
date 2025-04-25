package com.resuadam2.listamvvm.ui.list

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(
    // listScreenViewModel: ListScreenViewModel = viewModel(),
    listScreenViewModel: ListaScreenViewModelHibrido = viewModel(),
) {
    // val state by listScreenViewModel.uiState.collectAsState()
    val state = listScreenViewModel.uiState
    val tareas = listScreenViewModel.list
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Lista de Tareas")
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    IconButton(
                        onClick = listScreenViewModel::removeTareasMarcadas,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Eliminar tareas marcadas",
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Total de tareas : ${tareas.size}")
                    Text("Tareas marcadas : ${tareas.count { it.checked }}")
                }
            }
        }
    ) {
        ListaScreenContent(
            listScreenViewModel = listScreenViewModel,
            state = state,
            tareas = tareas,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun ListaScreenContent(
    listScreenViewModel: ListaScreenViewModelHibrido,
    state: ListaScreenStateHibrido,
    tareas: List<TareaUI> = emptyList(),
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ListaForm(
            titulo = state.titulo,
            descripcion = state.descripcion,
            buttonEnabled = state.addEnabled,
            editEnabled = state.editEnabled,
            onTituloChange = listScreenViewModel::onTituloChange,
            onDescripcionChange = listScreenViewModel::onDescripcionChange,
            onAgregar = listScreenViewModel::addTarea,
            onEditar = listScreenViewModel::editTarea,
        )
        LazyColumn {
            // items(state.tareas) {
            items(tareas) {
                TareaItem(
                    tarea = it,
                    expandirTarea = {
                        listScreenViewModel.expandirTarea(it)
                    },
                    onCheckedChange = {
                        listScreenViewModel.checkTarea(it)
                    },
                    onTareaEditar = {
                        listScreenViewModel.seleccionarTareaEditar(it)
                    },
                    eliminarTarea = {
                        listScreenViewModel.removeTarea(it)
                    },
                )
            }
        }
    }
}

@Composable
fun TareaItem(
    tarea: TareaUI,
    expandirTarea: () -> Unit,
    onCheckedChange: () -> Unit,
    onTareaEditar: () -> Unit,
    eliminarTarea: () -> Unit,
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
        Checkbox(
            checked = tarea.checked,
            onCheckedChange = {
                onCheckedChange()
            },
        )
        IconButton(
            onClick = onTareaEditar,
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Editar",
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
    titulo: String,
    descripcion: String,
    buttonEnabled: Boolean,
    editEnabled: Boolean,
    onTituloChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onAgregar: () -> Unit,
    onEditar: () -> Unit,
    context: Context = LocalContext.current,
) {
    Column {
        TextField(
            value = titulo,
            onValueChange = {
                onTituloChange(it)
            },
            label = {Text("Titulo*")},
        )
        TextField(
            value = descripcion,
            onValueChange = {
                onDescripcionChange(it)
            },
            label = { Text("Descripción*") },
        )
        if (!buttonEnabled) {
            Text(
                text = "Los campos con * son obligatorios",
                style = MaterialTheme.typography.labelSmall,
            )
        }
        Row {
            Button(
                onClick = onAgregar,
                enabled = buttonEnabled,
                /*
                onClick = {
                    if (titulo.isBlank() || descripcion.isBlank()) {
                        Toast.makeText(context, "Titulo o descripcion vacios", Toast.LENGTH_SHORT).show()
                    } else {
                        onAgregar
                    }
                },

                 */
            ) {
                Text("Agregar")
            }
            Button(
                onClick = onEditar,
                enabled = editEnabled,
            ) {
                Text("Editar")
            }
        }

    }
}