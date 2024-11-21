package com.example.musicwhisky1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.musicwhisky1.model.Artista

@Dao
interface ArtistaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirArtista(artista: Artista)

    @Query("SELECT * FROM artistas WHERE id = :idArtista")
    fun buscarArtistaPorId(idArtista: Int): LiveData<Artista>

    @Query("SELECT * FROM artistas")
    suspend fun buscarTodosArtista(): List<Artista>

    @Delete
    suspend fun deletarArtista(artista: Artista)

    @Update
    suspend fun atualizarArtista(artista: Artista)

}