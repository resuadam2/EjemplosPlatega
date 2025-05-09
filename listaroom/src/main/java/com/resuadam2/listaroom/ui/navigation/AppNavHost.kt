package com.resuadam2.listaroom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.resuadam2.listaroom.ui.add.AddDestination
import com.resuadam2.listaroom.ui.detail.DetailDestination
import com.resuadam2.listaroom.ui.edit.EditDestination
import com.resuadam2.listaroom.ui.home.HomeDestination
import com.resuadam2.listaroom.ui.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        composable(HomeDestination.route) {
            HomeScreen(
                onNavigateToAdd = {
                    navController.navigate(AddDestination.route)
                },
                onNavigateToDetail = { id ->
                    navController.navigate(DetailDestination.routeWithArgs.replace("{${DetailDestination.detailIdArg}}", id.toString()))
                },
                onNavigateToEdit = { id ->
                    navController.navigate(EditDestination.routeWithArgs.replace("{${EditDestination.editIdArg}}", id.toString()))
                },
            )
        }
        composable(AddDestination.route) {
            // AddScreen()
        }
        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.detailIdArg) {
                type = NavType.IntType
            })
        ) {
            // DetailScreen()
        }
        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.editIdArg) {
                type = NavType.IntType
            })
        ) {
            // EditScreen()
        }
    }
}