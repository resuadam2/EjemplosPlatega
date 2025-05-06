package com.resuadam2.listaroom.data

import kotlinx.coroutines.flow.Flow

interface PeliculaRepository {
    fun getAllPeliculas(): Flow<List<Pelicula>>
    fun getPeliculaById(id: Int): Flow<Pelicula>
    suspend fun insertPelicula(pelicula: Pelicula)
    suspend fun updatePelicula(pelicula: Pelicula)
    suspend fun deletePelicula(pelicula: Pelicula)
}

class PeliculaRepositoryImpl(private val peliculaDao: PeliculaDao) : PeliculaRepository  {
    override fun getAllPeliculas(): Flow<List<Pelicula>> {
        return peliculaDao.getAllPeliculas()
    }

    override fun getPeliculaById(id: Int): Flow<Pelicula> {
        return peliculaDao.getPeliculaById(id)
    }

    override suspend fun insertPelicula(pelicula: Pelicula) {
        peliculaDao.insertPelicula(pelicula)
    }

    override suspend fun updatePelicula(pelicula: Pelicula) {
        peliculaDao.updatePelicula(pelicula)
    }

    override suspend fun deletePelicula(pelicula: Pelicula) {
        peliculaDao.deletePelicula(pelicula)
    }
}