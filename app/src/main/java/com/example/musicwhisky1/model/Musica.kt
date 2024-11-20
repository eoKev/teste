package com.example.musicwhisky1.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.musicwhisky1.model.Artista

@Entity(tableName = "musicas",
    foreignKeys = [
        ForeignKey(
            entity = Artista::class, // classe na qual esta sendo pega a chave estrangeira
            parentColumns = ["idArtista"], // coluna na tabela Artistas
            childColumns = ["idArtista"], // coluna na tabela Musica
            onDelete = ForeignKey.CASCADE // Define op comporta mento em caso de delet de artista, o mesmo sera removido da tabela Musicas
        )
    ],
    indices = [Index(value = ["idArtista"])]
)
data class Musica (
    @PrimaryKey (autoGenerate = true )
    val idMusica: Int = 0,
    val idArtista: Int,
    val nomeMusica: String,
    val duralcao: String,
    val genero: String
)
