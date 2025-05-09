package com.resuadam2.listaroom.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resuadam2.listaroom.data.Pelicula
import com.resuadam2.listaroom.data.PeliculaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val peliculas: List<Pelicula> = listOf(),
    val showDeleteDialog: Boolean = false,
    val peliculaToDelete: Pelicula? = null,
)


class HomeViewModel (
    private val peliculaRepository: PeliculaRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadPeliculas()
        }
    }

    private suspend fun loadPeliculas() {
        peliculaRepository.getAllPeliculas().map {
            HomeUiState(
                peliculas = it,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        ).collect {
            _homeUiState.value = it
        }
    }

    fun deletePelicula(pelicula: Pelicula) {
        viewModelScope.launch {
            peliculaRepository.deletePelicula(pelicula)
        }
    }

    fun onShowOrHideDeleteDialog(show: Boolean, pelicula: Pelicula?) {
        _homeUiState.value = _homeUiState.value.copy(
            showDeleteDialog = show,
            peliculaToDelete = if (show) pelicula else null
        )
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}