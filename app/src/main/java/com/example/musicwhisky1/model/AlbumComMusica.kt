package com.example.musicwhisky1.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.app.entidades.AlbumMusicaCrossRef
import com.example.musicwhisky.Album

data class AlbumComMusicas(
    @Embedded val album: Album,
    @Relation(
        parentColumn = "id",
        entityColumn = "idAlbum"
    )
    val musicas: List<Musica>
)