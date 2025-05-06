package com.resuadam2.listaroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDao {
    @Query("SELECT * FROM peliculas")
    fun getAllPeliculas(): Flow<List<Pelicula>>

    @Query("SELECT * FROM peliculas WHERE id = :id")
    fun getPeliculaById(id: Int): Flow<Pelicula>

    @Insert
    suspend fun insertPelicula(pelicula: Pelicula)

    @Update
    suspend fun updatePelicula(pelicula: Pelicula)

    @Delete
    suspend fun deletePelicula(pelicula: Pelicula)
}