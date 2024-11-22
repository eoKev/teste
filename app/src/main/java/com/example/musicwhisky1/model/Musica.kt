package com.example.musicwhisky1.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.musicwhisky.Album

@Entity(tableName = "musicas",
    foreignKeys = [
        ForeignKey(
            entity = Artista::class, // classe na qual esta sendo pega a chave estrangeira
            parentColumns = ["id"], // coluna na tabela Artistas
            childColumns = ["idArtista"], // coluna na tabela Musica
            onDelete = ForeignKey.CASCADE // Define o comportamento em caso de delet de artista, o mesmo sera removido da tabela Musicas
        ),
        ForeignKey(
            entity = Album::class,
            parentColumns = ["id"],
            childColumns = ["idAlbum"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["idArtista"]),Index(value = ["idAlbum"])]

)
data class Musica (
    @PrimaryKey (autoGenerate = true )
    val id: Int = 0,
    @ColumnInfo(name = "idArtista")
    val idArtista: Int,
    @ColumnInfo(name = "idAlbum")
    val idAlbum: Int,
    val nome: String,
    val duracao: String,
)
