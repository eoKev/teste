package com.example.musicwhisky1.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.app.entidades.AlbumMusicaCrossRef
import com.example.musicwhisky.Album

data class AlbumComMusicas(
    @Embedded val album: Album,
    @Relation(
        parentColumn = "idAlbum",
        entityColumn = "idMusica",
        associateBy = Junction(AlbumMusicaCrossRef::class)
    )
    val musicas: List<Musica>
)