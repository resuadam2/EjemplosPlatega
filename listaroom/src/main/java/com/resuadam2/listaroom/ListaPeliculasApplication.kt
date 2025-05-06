package com.resuadam2.listaroom

import android.app.Application
import com.resuadam2.listaroom.data.AppContainer

class ListaPeliculasApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}