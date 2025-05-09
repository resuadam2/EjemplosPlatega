package com.resuadam2.listaroom.ui.edit

import com.resuadam2.listaroom.R
import com.resuadam2.listaroom.ui.navigation.NavigationDestination

object EditDestination : NavigationDestination {
    override val route = "edit"
    override val title = R.string.edit_title
    const val editIdArg = "editId"
    val routeWithArgs = "$route/{$editIdArg}"
}