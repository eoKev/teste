package com.example.musicwhisky1.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.musicwhisky.Album

@Dao
interface AlbumDao {

    @Insert
    suspend fun inserir(album: Album)

    @Query("SELECT * FROM albuns")
    suspend fun buscarTodos(): List<Album>

    @Delete
    suspend fun deletar(album: Album)

    @Update
    suspend fun atualizar(album: Album)

}