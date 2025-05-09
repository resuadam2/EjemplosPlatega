package com.resuadam2.listaroom.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.resuadam2.listaroom.data.PeliculaRepository
import com.resuadam2.listaroom.ui.add.PeliculaDetails
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.resuadam2.listaroom.ui.add.toPeliculaDetails
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class DetailUiState(
    val PeliculaDetails: PeliculaDetails = PeliculaDetails(),
)

class DetailViewModel (
    private val peliculaRepository: PeliculaRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    companion object {
        private const val DETAIL_ID = "detailId"
    }

    var detailUiState by mutableStateOf(DetailUiState())
        private set

    private val peliculaId: Int = checkNotNull(savedStateHandle[DetailDestination.detailIdArg])

    init {
        val peliculaDetails = peliculaRepository.getPeliculaById(peliculaId).map {
            it.toPeliculaDetails()
        }
        viewModelScope.launch {
            peliculaDetails.collect {
                detailUiState = detailUiState.copy(
                    PeliculaDetails = it,
                )
            }
        }
    }
}