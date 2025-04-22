package com.resuadam2.ejemplomvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.resuadam2.ejemplomvvm.ui.screens.VariosContadoresScreen
import com.resuadam2.ejemplomvvm.ui.theme.CursoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoTheme {
                   VariosContadoresScreen()
            }
        }
    }
}

