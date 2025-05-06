package com.resuadam2.listamvvm.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.resuadam2.listamvvm.data.Tarea
import com.resuadam2.listamvvm.data.tareasExample
import com.resuadam2.listamvvm.ui.list.TareaUI


data class ListaScreenStateHibrido(
    val titulo: String = "",
    val descripcion: String = "",
    val addEnabled: Boolean = false,
    val editEnabled: Boolean = false,
    val tareaSeleccionada: TareaUI? = null,
)

data class TareaUI(
    val titulo: String,
    val descripcion: String,
    val expanded: Boolean = false,
    val checked: Boolean = false,
)

fun Tarea.toTareaUI(): TareaUI {
    return TareaUI(
        titulo = titulo,
        descripcion = descripcion,
    )
}

/*
fun TareaUI.toTarea(): Tarea {
    return Tarea(
        titulo = titulo,
        descripcion = descripcion,
    )
 */

class ListaScreenViewModelHibrido : ViewModel() {
    var uiState by mutableStateOf(ListaScreenStateHibrido())
        private set

    private val _list = mutableStateListOf<TareaUI>()
    val list: List<TareaUI> get() = _list.toList()



    init {
        _list.addAll(tareasExample.map {
            it.toTareaUI()
        })
    }

    fun onTituloChange(titulo: String) {
        uiState = uiState.copy(titulo = titulo)
        checkAddEnabled()
        checkEditEnabled()
    }

    fun onDescripcionChange(descripcion: String) {
        uiState = uiState.copy(descripcion = descripcion)
        checkAddEnabled()
        checkEditEnabled()
    }

    fun checkAddEnabled() {
        uiState = uiState.copy(
            addEnabled = uiState.titulo.isNotBlank() && uiState.descripcion.isNotBlank() && !uiState.editEnabled,
        )
    }

    fun addTarea() {
        val newTarea = TareaUI(
            titulo = uiState.titulo,
            descripcion = uiState.descripcion,
        )
        _list.add(newTarea)
        uiState = uiState.copy(
            titulo = "",
            descripcion = "",
            addEnabled = false,
        )
    }

    fun removeTarea(tarea: TareaUI) {
        _list.remove(tarea)
    }

    fun expandirTarea(tarea: TareaUI) {
        /*
        _list.find {
            it == tarea
        }?.let {
            it.expanded = !it.expanded
        }

         */
        _list[_list.indexOf(tarea)] = tarea.copy(
            expanded = !tarea.expanded
        )
    }

    fun editTarea() {
        _list[_list.indexOf(uiState.tareaSeleccionada)] = uiState.tareaSeleccionada!!.copy(
            titulo = uiState.titulo,
            descripcion = uiState.descripcion,
        )
        uiState = uiState.copy(
            titulo = "",
            descripcion = "",
            editEnabled = false,
            tareaSeleccionada = null
        )
    }

    fun checkEditEnabled() {
        uiState = uiState.copy(
            editEnabled = uiState.titulo.isNotBlank() && uiState.descripcion.isNotBlank() && uiState.tareaSeleccionada != null,
        )
    }

    fun seleccionarTareaEditar(tarea: TareaUI) {
        uiState = uiState.copy(
            titulo = tarea.titulo,
            descripcion = tarea.descripcion,
            editEnabled = true,
            tareaSeleccionada = tarea
        )
    }

    fun checkTarea(tarea: TareaUI) {
        _list[_list.indexOf(tarea)] = tarea.copy(
            checked = !tarea.checked
        )
    }

    fun removeTareasMarcadas() {
        _list.removeAll { it.checked }
    }
}