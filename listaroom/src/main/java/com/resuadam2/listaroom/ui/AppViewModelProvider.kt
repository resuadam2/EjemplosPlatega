package com.resuadam2.listaroom.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.resuadam2.listaroom.ListaPeliculasApplication
import com.resuadam2.listaroom.ui.add.AddViewModel
import com.resuadam2.listaroom.ui.detail.DetailViewModel
import com.resuadam2.listaroom.ui.home.HomeViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                peliculaRepository = peliculasApp().container.providePeliculaRepository()
            )
        }
        initializer {
            AddViewModel(
                peliculaRepository = peliculasApp().container.providePeliculaRepository()
            )
        }
        initializer {
            DetailViewModel(
                peliculaRepository = peliculasApp().container.providePeliculaRepository(),
                savedStateHandle = this.createSavedStateHandle()
            )
        }
    }
}

fun CreationExtras.peliculasApp(): ListaPeliculasApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ListaPeliculasApplication)