package com.resuadam2.listaroom.ui.add
import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.mutableStateOf
import com.resuadam2.listaroom.data.Pelicula
import com.resuadam2.listaroom.data.PeliculaRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class AddUiState(
    val peliculaDetails: PeliculaDetails = PeliculaDetails(),
    val isAddButtonEnabled: Boolean = false,
)

data class PeliculaDetails(
    val id: Int = 0,
    val titulo: String = "",
    val anio: String = "",
    val duracion: String = "",
    val genero: String = "",
    val valoracion: String = "1",
)

fun Pelicula.toPeliculaDetails(): PeliculaDetails {
    return PeliculaDetails(
        id = id,
        titulo = titulo,
        anio = anio.toString(),
        duracion = duracion.toString(),
        genero = genero,
        valoracion = valoracion.toString(),
    )
}

fun PeliculaDetails.toPelicula(): Pelicula {
    return Pelicula(
        id = id,
        titulo = titulo,
        anio = anio.toInt(),
        duracion = duracion.toInt(),
        genero = genero,
        valoracion = valoracion.toInt(),
    )
}

class AddViewModel (
    private val peliculaRepository: PeliculaRepository
) : ViewModel() {
    var addUiState by mutableStateOf(AddUiState())
        private set

    fun updateUiState(peliculaDetails: PeliculaDetails) {
        addUiState = addUiState.copy(
            peliculaDetails = peliculaDetails,
            isAddButtonEnabled = validateInputs()
        )
    }

    private fun validateInputs(details: PeliculaDetails = addUiState.peliculaDetails): Boolean {
        return with(details) {
            titulo.isNotBlank() &&
            anio.isNotBlank() &&
            duracion.isNotBlank() &&
            genero.isNotBlank() &&
            valoracion.isNotBlank() &&
                    valoracion.toInt() in 1..5 &&
                    anio.toInt() in 1900..2100 &&
                    duracion.toInt() in 1..300
        }
    }

    suspend fun addPelicula() : Boolean {
        if (validateInputs()) {
            try {
                peliculaRepository.insertPelicula(addUiState.peliculaDetails.toPelicula())
                return true
            } catch (e: SQLiteConstraintException) {
                return false
            }
        } else return false
    }
}