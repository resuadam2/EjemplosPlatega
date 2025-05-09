package com.resuadam2.listaroom.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.TableInfo
import com.resuadam2.listaroom.ListaPeliculasTopAppBar
import com.resuadam2.listaroom.ui.navigation.NavigationDestination
import com.resuadam2.listaroom.R
import com.resuadam2.listaroom.ui.AppViewModelProvider
import com.resuadam2.listaroom.ui.add.AddDestination
import com.resuadam2.listaroom.ui.add.PeliculaDetails


object DetailDestination : NavigationDestination{
    override val route = "detail"
    override val title = R.string.detail_title
    const val detailIdArg = "detailId"
    val routeWithArgs = "$route/{$detailIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            ListaPeliculasTopAppBar(
                title = stringResource(DetailDestination.title),
                canNavigateBack = true,
                onNavigateUp = onNavigateBack
            )
        },
    ) {
        DetailsBody(
            peliculaDetails = viewModel.detailUiState.PeliculaDetails,
            modifier = modifier.padding(it)
        )
    }
}

@Composable
fun DetailsBody(peliculaDetails: PeliculaDetails, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RowDetails(
            title = "Titulo",
            value = peliculaDetails.titulo,
            modifier = Modifier.padding(8.dp)
        )
        RowDetails(
            title = "Genero",
            value = peliculaDetails.genero,
            modifier = Modifier.padding(8.dp)
        )
        RowDetails(
            title = "Duracion",
            value = peliculaDetails.duracion,
            modifier = Modifier.padding(8.dp)
        )
        RowDetails(
            title = "Publicacion",
            value = peliculaDetails.anio,
            modifier = Modifier.padding(8.dp)
        )
        RowDetails(
            title = "Valoracion",
            value = peliculaDetails.valoracion,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun RowDetails(title: String, value: String, modifier: Modifier) {
    Text(
        text = "$title: $value",
        modifier = modifier,
        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
    )
}