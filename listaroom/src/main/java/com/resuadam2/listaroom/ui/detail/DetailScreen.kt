package com.resuadam2.listaroom.ui.detail

import com.resuadam2.listaroom.ui.navigation.NavigationDestination
import com.resuadam2.listaroom.R


object DetailDestination : NavigationDestination{
    override val route = "detail"
    override val title = R.string.detail_title
    const val detailIdArg = "detailId"
    val routeWithArgs = "$route/{$detailIdArg}"
}