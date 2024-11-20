package com.example.app.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.musicwhisky.Album
import com.example.musicwhisky1.model.Musica

@Entity(
    tableName = "album_musica_cross_ref",
    primaryKeys = ["idAlbum", "idMusica"],
    foreignKeys = [
        ForeignKey(
            entity = Album::class,
            parentColumns = ["idAlbum"],
            childColumns = ["idAlbum"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Musica::class,
            parentColumns = ["idMusica"],
            childColumns = ["idMusica"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["idAlbum"]),
        Index(value = ["idMusica"])
    ]
)
data class AlbumMusicaCrossRef(
    val idAlbum: Int,
    val idMusica: Int
)
