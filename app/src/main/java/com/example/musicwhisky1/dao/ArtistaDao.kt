package com.example.musicwhisky1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.musicwhisky1.model.Artista
import com.example.musicwhisky1.model.Musica

@Dao
interface ArtistaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(artista: Artista)

    @Query("SELECT * FROM artistas WHERE id = :idArtista")
    fun buscarPorId(idArtista: Int): LiveData<Artista>

    @Query("SELECT * FROM artistas")
    suspend fun buscarTodos(): List<Artista>

    @Delete
    suspend fun deletar(artista: Artista)

    @Update
    suspend fun atualizar(artista: Artista)

    @Query("SELECT * FROM artistas WHERE nome LIKE '%' || :nome || '%'")
    fun buscarPorNome(nome: String): LiveData<List<Artista>>


}