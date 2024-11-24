package com.example.musicwhisky1.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.musicwhisky.Album
@Entity(
    tableName = "musicas",
    foreignKeys = [
        ForeignKey(
            entity = Album::class,
            parentColumns = ["id"],
            childColumns = ["idAlbum"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Artista::class,
            parentColumns = ["id"],
            childColumns = ["idArtista"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["idAlbum"]), Index(value = ["idArtista"])]
)
data class Musica(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "idAlbum")
    val idAlbum: Int,
    @ColumnInfo(name = "idArtista")
    val idArtista: Int,
    val nome: String,
    val duracao: Int
)