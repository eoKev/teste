package com.example.musicwhisky1.model

import androidx.room.Embedded
import androidx.room.Relation

data class ArtistaComAlbuns(
    @Embedded val artista: Artista,
    @Relation(
        parentColumn = "id",
        entityColumn = "idArtista"
    )
    val artistas : List<Artista>
)