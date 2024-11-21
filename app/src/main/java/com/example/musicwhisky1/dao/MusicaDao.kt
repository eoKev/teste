package com.example.musicwhisky1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.musicwhisky1.model.Musica

@Dao
interface MusicaDao {

    @Insert
    suspend fun inserir(musica: Musica)

    // Obter todas as músicas
    @Query("SELECT * FROM musicas")
    fun listarMusicas(): LiveData<List<Musica>>

    // Obter uma música específica pelo ID
    @Query("SELECT * FROM musicas WHERE id = :musicaId")
    fun ListarMusicaPorId(musicaId: Int): LiveData<Musica>

    @Delete
    suspend fun deletarMusica(musica: Musica)

    @Update
    suspend fun atualizarMusica(musica: Musica)

}