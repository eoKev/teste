package com.example.musicwhisky1.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.app.entidades.AlbumMusicaCrossRef
import com.example.musicwhisky.Album
import com.example.musicwhisky1.model.AlbumComMusicas

interface AlbumMusicaCrossRefDao {
    // listar as musicas de um album
    @Transaction
    @Query("SELECT * FROM albuns WHERE id = :albumId")
    fun obterAlbumComMusicas(albumId: Int): LiveData<AlbumComMusicas>

    // adicionar musicas a um album
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adicionarMusicaAoAlbum(crossRef: AlbumMusicaCrossRef)

    // deletar musica especifica de um album
    @Query("DELETE FROM album_musica_cross_ref WHERE idAlbum = :albumId AND idMusica = :musicaId")
    suspend fun removerMusicaDoAlbum(albumId: Int, musicaId: Int)

    // deletar um album e suas associações com musicas
    @Transaction
    suspend fun excluirAlbumEAssociados(album: Album) {
        excluirAssociacoesDoAlbum(album.id)
        excluirAlbumBase(album)
    }
    @Delete
    suspend fun excluirAlbumBase(album: Album)

    // excluir todas as associações do album na tabela intermediara
    @Query("DELETE FROM album_musica_cross_ref WHERE idAlbum = :albumId")
    suspend fun excluirAssociacoesDoAlbum(albumId: Int)

}