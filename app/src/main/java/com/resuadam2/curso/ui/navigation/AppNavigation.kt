package com.resuadam2.curso.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.resuadam2.curso.ui.screens.ContadorScreen
import com.resuadam2.curso.ui.screens.ContadoresAvanzadosScreen
import com.resuadam2.curso.ui.screens.EjemploComposablesScreen
import com.resuadam2.curso.ui.screens.HomeScreen
import com.resuadam2.curso.ui.screens.ParametrosScreen
import com.resuadam2.curso.ui.screens.VariosScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name
    ) {
        composable(route = AppScreens.HomeScreen.name) {
            HomeScreen(
                onNavigateToEjemploComposables = {
                    navController.navigate(AppScreens.EjemploComposablesScreen.name)
                },
                onNavigateToContadorSimple = {
                    navController.navigate(AppScreens.ContadorSimpleScreen.name)
                },
                onNavigateToVariosContadores = {
                    navController.navigate(AppScreens.VariosContadoresScreen.name)
                },
                onNavigateToContadoresAvanzados = {
                    navController.navigate(AppScreens.ContadoresAvanzadosScreen.name)
                },
                onNavigateToParametros = {
                    navController.navigate(AppScreens.ParametrosScreen.name)
                },
                onNavigateToParametrosConParam = { param ->
                    navController.navigate(AppScreens.ParametrosScreen.name + "/$param")
                }
            )
        }
        composable(route = AppScreens.EjemploComposablesScreen.name) {
            EjemploComposablesScreen()
        }
        composable(route = AppScreens.ContadorSimpleScreen.name) {
            ContadorScreen()
        }
        composable(route = AppScreens.VariosContadoresScreen.name) {
            VariosScreen()
        }
        composable(route = AppScreens.ContadoresAvanzadosScreen.name) {
            ContadoresAvanzadosScreen()
        }
        composable(route = AppScreens.ParametrosScreen.name) {
            ParametrosScreen(param = "Sin Parametro")
        }
        composable(
            route = AppScreens.ParametrosScreen.name + "/{param}",
            arguments = listOf(navArgument(name = "param") {type = NavType.StringType})
        ) {
            val param = it.arguments?.getString("param")
            ParametrosScreen(param = param ?: "Sin Parametro")
        }
    }
}