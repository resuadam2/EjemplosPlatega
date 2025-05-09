package com.resuadam2.listaroom.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.resuadam2.listaroom.ListaPeliculasTopAppBar
import com.resuadam2.listaroom.R
import com.resuadam2.listaroom.data.Pelicula
import com.resuadam2.listaroom.ui.AppViewModelProvider
import com.resuadam2.listaroom.ui.navigation.NavigationDestination
import androidx.compose.runtime.getValue



object HomeDestination : NavigationDestination {
    override val route = "home"
    override val title = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAdd: () -> Unit,
    onNavigateToEdit: (Int) -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val state by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            ListaPeliculasTopAppBar(
                title = stringResource(HomeDestination.title),
                canNavigateBack = false,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.agregar_pelicula),
                )
            }
        }
    ) { paddingValues ->
        HomeBody(
            peliculas = state.peliculas,
            onNavigateToDetail = onNavigateToDetail,
            onNavigateToEdit = onNavigateToEdit,
            onShowOrHideDeleteDialog = viewModel::onShowOrHideDeleteDialog,
            modifier = Modifier.padding(paddingValues)
        )
        if (state.showDeleteDialog) {
            DeleteConfirmationDialog(
                title = state.peliculaToDelete?.titulo ?: "",
                onDeleteConfirm = {
                    viewModel.deletePelicula(state.peliculaToDelete!!)
                },
                onDeleteDismiss = {
                    viewModel.onShowOrHideDeleteDialog(false, null)
                }
            )
        }
    }
}

@Composable
fun HomeBody(
    peliculas: List<Pelicula>,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToEdit: (Int) -> Unit,
    onShowOrHideDeleteDialog: (Boolean, Pelicula) -> Unit,
    modifier: Modifier,
) {
    if (peliculas.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Todavía no hay películas añadidas")
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            items(peliculas) {
                PeliculaItem(
                    pelicula = it,
                    onNavigateToDetail = onNavigateToDetail,
                    onNavigateToEdit = onNavigateToEdit,
                    onShowOrHideDeleteDialog = onShowOrHideDeleteDialog,
                )
            }
        }
    }
}

@Composable
fun PeliculaItem(
    pelicula: Pelicula,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToEdit: (Int) -> Unit,
    onShowOrHideDeleteDialog: (Boolean, Pelicula) -> Unit,
) {
    Row {
        Text(pelicula.titulo)
        Row {
            for (i in 1..5) {
                Icon(
                    imageVector = if (i <= pelicula.valoracion) Icons.Filled.Star else Icons.TwoTone.Star,
                    contentDescription = "valoracion",
                )
            }
        }
        IconButton(
            onClick = {
                onNavigateToDetail(pelicula.id)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(R.string.detail_title),
            )
        }
        IconButton(
            onClick = {
                onNavigateToEdit(pelicula.id)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.edit_title),
            )
        }
        IconButton(
            onClick = {
                onShowOrHideDeleteDialog(true, pelicula)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_title),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteConfirmationDialog(
    title: String,
    onDeleteConfirm: () -> Unit,
    onDeleteDismiss: () -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = onDeleteDismiss,
    ) {
        Column {
            Text(
                text = "¿Seguro que quiere borrar $title ?",
                modifier = Modifier.padding(16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(16.dp)
            ) {
                IconButton(
                    onClick = onDeleteDismiss
                ) {
                    Text(stringResource(R.string.cancel))
                }
                IconButton(
                    onClick = onDeleteConfirm
                ) {
                    Text(stringResource(R.string.delete))
                }
            }
        }
    }
}