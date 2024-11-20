package com.example.musicwhisky.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MusicaDao {

    @Insert
    suspend fun inserir(musica: Musica)

    @Query("SELECT * FROM musicas")
    suspend fun buscarTodos(): List<Musica>

    @Delete
    suspend fun deletar(musica: Musica)

    @Update
    suspend fun atualizar(musica: Musica)

}