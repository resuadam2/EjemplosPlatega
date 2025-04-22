package com.resuadam2.curso.ui.navigation

enum class AppScreens {
    HomeScreen,
    EjemploComposablesScreen,
    ContadorSimpleScreen,
    VariosContadoresScreen,
    ContadoresAvanzadosScreen,
    ParametrosScreen,
    ListaScreen,
}

/*
sealed class AppScreens(
    val route: String
) {
    object HomeScreen: AppScreens("home_screen")
    object EjemploComposablesScreen: AppScreens("ejemplo_composables_screen")
    object ContadorSimpleScreen: AppScreens("contador_simple_screen")
    object VariosContadoresScreen: AppScreens("varios_contadores_screen")
    object ContadoresAvanzadosScreen: AppScreens("contadores_avanzados_screen")
}
 */

/*
interface NavigationDestination {
    val route: String
    val titleRes: Int
}
 */