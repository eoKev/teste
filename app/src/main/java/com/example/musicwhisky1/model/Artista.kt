package com.example.musicwhisky1.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artistas")
data class Artista(
    @PrimaryKey(autoGenerate = true)
    val idArtista: Int = 0,
    val nomeArtista: String
)