package com.resuadam2.ejemplomvvm.ui.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class VariosContadoresState(
    val contadorA: Int = 0,
    val contadorB: Int = 0,
    val contadorTotal: Int = 0,
)

class VariosContadoresViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VariosContadoresState())
    val uiState: StateFlow<VariosContadoresState> = _uiState.asStateFlow()

    /*
    var uiStateUnica by mutableStateOf(VariosContadoresState())
        private set
    */

    // Scope que se ejecuta cuándo se instancia el ViewModel
    init {

    }

    fun incrementarA() {
        _uiState.value = _uiState.value.copy(
            contadorA = _uiState.value.contadorA + 1,
            contadorTotal = _uiState.value.contadorTotal + 1,
        )
    }

    fun reiniciarA() {
        _uiState.value = _uiState.value.copy(
            contadorA = 0,
        )
    }

    fun incrementarB() {
        _uiState.value = _uiState.value.copy(
            contadorB = _uiState.value.contadorB + 1,
            contadorTotal = _uiState.value.contadorTotal + 1,
            )
    }

    fun reiniciarB() {
        _uiState.value = _uiState.value.copy(
            contadorB = 0,
        )
    }

    fun reiniciarTotal() {
        _uiState.value = _uiState.value.copy(
            contadorTotal = 0,
        )
    }
}