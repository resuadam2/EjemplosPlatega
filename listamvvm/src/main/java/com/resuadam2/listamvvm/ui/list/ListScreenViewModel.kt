package com.resuadam2.listamvvm.ui.list

import androidx.lifecycle.ViewModel
import com.resuadam2.listamvvm.data.Tarea
import com.resuadam2.listamvvm.data.tareasExample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ListaScreenState(
    // val tareas: MutableList<Tarea> = tareasExample.toMutableList(),
    val tareas: List<TareaUI> = emptyList(),
    val titulo: String = "",
    val descripcion: String = "",
)

class ListScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ListaScreenState())
    val uiState: StateFlow<ListaScreenState> = _uiState.asStateFlow()

    init {
        _uiState.value = ListaScreenState(
            // tareas = tareasExample.toMutableList(),
            tareas = tareasExample.map{
                it.toTareaUI()
            },
        )
    }

    fun onTituloChange(titulo: String) {
        _uiState.value = _uiState.value.copy(titulo = titulo)
    }

    fun onDescripcionChange(descripcion: String) {
        _uiState.value = _uiState.value.copy(descripcion = descripcion)
    }

    fun addTarea() {
        val newTarea = TareaUI(
            titulo = _uiState.value.titulo,
            descripcion = _uiState.value.descripcion,
        )
        // _uiState.value.tareas.add(newTarea)
        _uiState.value = _uiState.value.copy(
            tareas = _uiState.value.tareas + newTarea,
            titulo = "",
            descripcion = "",
        )
    }

    fun removeTarea(tarea: TareaUI) {
        // _uiState.value.tareas.remove(tarea)
        _uiState.value = _uiState.value.copy(
            tareas = _uiState.value.tareas - tarea,
        )
    }

    fun expandirTarea(tarea: TareaUI) {
        /*
        _uiState.value.tareas.find {
            it == tarea
        }?.let {
            it.expanded = !it.expanded
        }
         */
        _uiState.value = _uiState.value.copy(
            tareas = _uiState.value.tareas.map {
                if (it == tarea) {
                    it.copy(expanded = !it.expanded)
                } else {
                    it
                }
            }
        )
    }

}