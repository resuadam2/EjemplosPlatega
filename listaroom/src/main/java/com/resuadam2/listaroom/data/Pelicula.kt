package com.resuadam2.listaroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas")
data class Pelicula(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // @PrimaryKey val titulo: String,
    val titulo: String,
    val anio: Int,
    val duracion: Int,
    val genero: String,
    val valoracion: Int,
    // @ForeignKey(entity = Actor::class, parentColumns = ["id"], childColumns = ["actorId"]) val actorId: Int,
)