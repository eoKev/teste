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
    suspend fun inserir(artista: Artista)

    @Query("SELECT * FROM artistas WHERE id = :id")
    fun buscar(id: Int): LiveData<Artista>

    @Query("SELECT * FROM artistas")
    suspend fun buscarTodos(): List<Artista>

    @Delete
    suspend fun deletar(artista: Artista)

    @Update
    suspend fun atualizar(artista: Artista)

}