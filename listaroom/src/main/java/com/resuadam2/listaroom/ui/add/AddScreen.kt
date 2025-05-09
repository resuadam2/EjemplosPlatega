package com.resuadam2.listaroom.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.resuadam2.listaroom.ListaPeliculasTopAppBar
import com.resuadam2.listaroom.ui.navigation.NavigationDestination
import com.resuadam2.listaroom.R
import com.resuadam2.listaroom.ui.AppViewModelProvider
import kotlinx.coroutines.launch


object AddDestination : NavigationDestination {
    override val route = "add"
    override val title = R.string.add_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar =  {
            ListaPeliculasTopAppBar(
                title = stringResource(AddDestination.title),
                canNavigateBack = true,
                onNavigateUp = onNavigateBack
            )
        }
    ) {
        AddBody(
            addUiState = viewModel.addUiState,
            onValueChange = viewModel::updateUiState,
            onAdd = {
                coroutineScope.launch {
                    viewModel.addPelicula()
                }
                onNavigateBack()
            },
            modifier = modifier.padding(it)
        )
    }
}

@Composable
fun AddBody(
    addUiState: AddUiState,
    onValueChange: (PeliculaDetails) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier
) {
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        AddForm(
            peliculaDetails = addUiState.peliculaDetails,
            onValueChange = onValueChange,
        )
        Button(
            onClick = onAdd,
            enabled = addUiState.isAddButtonEnabled
        ) {
            Text(stringResource(R.string.add_button))
        }
    }
}

@Composable
fun AddForm(peliculaDetails: PeliculaDetails, onValueChange: (PeliculaDetails) -> Unit) {
    Column {
        FormRow(
            label = stringResource(R.string.titulo),
            value = peliculaDetails.titulo,
            onValueChange = { onValueChange(peliculaDetails.copy(titulo = it))}
        )
        FormRow(
            label = stringResource(R.string.anio),
            value = peliculaDetails.anio,
            onValueChange = { onValueChange(peliculaDetails.copy(anio = it))}
        )
        FormRow(
            label = stringResource(R.string.genero),
            value = peliculaDetails.genero,
            onValueChange = { onValueChange(peliculaDetails.copy(genero = it))}
        )
        FormRow(
            label = stringResource(R.string.duracion),
            value = peliculaDetails.duracion,
            onValueChange = { onValueChange(peliculaDetails.copy(duracion = it))}
        )
        RattingRow(
            label = stringResource(R.string.valoracion),
            selectedRating = peliculaDetails.valoracion.toInt(),
            onValueChange = { onValueChange(peliculaDetails.copy(valoracion = it.toString()))}
        )
    }
}

@Composable
fun RattingRow(label: String, selectedRating: Int, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(0.3f),
        ){
            Text(label)
        }
        Box(
            modifier = Modifier.weight(0.7f)
        ) {
            Row {
                for (i in 1..5) {
                    IconButton(
                        onClick = {
                            onValueChange(i.toString())
                        }
                    ) {
                        if(i <= selectedRating) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = stringResource(R.string.star_description),
                            )
                        } else {
                            Icon(
                                imageVector = Icons.TwoTone.Star,
                                contentDescription = stringResource(R.string.star_description),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FormRow(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(0.3f),
        ){
            Text(label)
        }
        Box(
            modifier = Modifier.weight(0.7f)
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}