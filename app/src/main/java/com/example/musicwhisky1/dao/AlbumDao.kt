package com.example.musicwhisky1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.app.entidades.AlbumMusicaCrossRef
import com.example.musicwhisky.Album
import com.example.musicwhisky1.model.AlbumComMusicas
import com.example.musicwhisky1.model.Artista

@Dao
interface AlbumDao {
    // Inserir Album
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(album: Album)

    // Obter todos os álbuns
    @Query("SELECT * FROM albuns")
    suspend fun buscarTodos(): List<Album>

    //Obeter um album especifico pelo Id
    @Query("SELECT * FROM albuns WHERE id =:albumId")
    fun listarPorId( albumId: Int): LiveData<Album>

    @Delete
    suspend fun deletar(album: Album)

    @Update
    suspend fun atualizar(album: Album)
    @Transaction
    @Query("SELECT * FROM albuns")
    fun listarAlbumComMusicas() : List<AlbumComMusicas>

    @Query("SELECT * FROM albuns WHERE id = :id")
    fun buscarPorId(id: Int): LiveData<Album>

    @Query("SELECT * FROM albuns WHERE nome LIKE '%' || :nome || '%'")
    fun buscarPorNome(nome: String): LiveData<List<Album>>

    @Insert
    suspend fun insert(artistas: List<Album>)

}