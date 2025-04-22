package com.resuadam2.curso.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.resuadam2.curso.R
import com.resuadam2.curso.ui.navigation.AppScreens

/*
object HomeScreen : NavigationDestination {
    override val route: String = "home_screen"
    override val titleRes: Int = R.string.app_name
}
 */

@Composable
fun HomeScreen(
    onNavigateToEjemploComposables: () -> Unit,
    onNavigateToContadorSimple: () -> Unit,
    onNavigateToVariosContadores: () -> Unit,
    onNavigateToContadoresAvanzados: () -> Unit,
    onNavigateToParametros: () -> Unit,
    onNavigateToParametrosConParam: (String) -> Unit,
    onNavigateToListaScreen: () -> Unit,
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onNavigateToEjemploComposables,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = stringResource(R.string.ejemplo_composables))
        }
        Button(
            onClick =onNavigateToContadorSimple,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Ejemplo Contador Simple")
        }
        Button(
            onClick = onNavigateToVariosContadores,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Ejemplo Varios Contadores")
        }
        Button(
            onClick = onNavigateToContadoresAvanzados,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Ejemplo Contadores Avanzados")
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Button(
                onClick = onNavigateToParametros,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Ejemplo Parametros sin pasarle param")
            }
            var texto by remember { mutableStateOf("") }
            TextField(
                value = texto,
                onValueChange = {
                    texto = it
                },
                label = { "Texto a pasar" },
            )
            Button(
                onClick = {
                    onNavigateToParametrosConParam(texto)
                },
            ) {
                Text(text = "Ejemplo Parametros con param")
            }
        }
        Button(
            onClick = onNavigateToListaScreen,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Ejemplo Lista Screen")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        {},
        {},
        {},
        {},
        {},
        {},
        {},
    )
}