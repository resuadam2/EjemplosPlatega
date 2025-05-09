package com.resuadam2.listaroom.data

import android.content.Context

class AppContainer(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)
    private val peliculaDao = appDatabase.peliculaDao()
    private val peliculaRepository = PeliculaRepositoryImpl(peliculaDao)

    fun providePeliculaRepository() : PeliculaRepository {
        return peliculaRepository
    }
}