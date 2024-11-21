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

@Dao
interface AlbumDao {
    // Inserir Album
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirAlbum(album: Album)

    // Obter todos os álbuns
    @Query("SELECT * FROM albuns")
    fun listarAlbuns(): LiveData<List<Album>>

    //Obeter um album especifico pelo Id
    @Query("SELECT * FROM albuns WHERE idAlbum =:albumId")
    fun listarAlbumPorId( albumId: Int): LiveData<Album>

    @Delete
    suspend fun deletarAlbum(album: Album)

    @Update
    suspend fun atualizarAlbum(album: Album)





}