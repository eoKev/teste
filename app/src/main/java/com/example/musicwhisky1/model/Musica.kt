package com.example.musicwhisky1.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "musicas",
    foreignKeys = [
        ForeignKey(
            entity = Artista::class, // classe na qual esta sendo pega a chave estrangeira
            parentColumns = ["id"], // coluna na tabela Artistas
            childColumns = ["idArtista"], // coluna na tabela Musica
            onDelete = ForeignKey.CASCADE // Define o comportamento em caso de delet de artista, o mesmo sera removido da tabela Musicas
        )
    ],
    indices = [Index(value = ["idArtista"])]
)
data class Musica (
    @PrimaryKey (autoGenerate = true )
    val id: Int = 0,
    val idArtista: Int,
    val nome: String,
    val duracao: String,
)
