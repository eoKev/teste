package com.example.musicwhisky1.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artistas")
data class Artista(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String
)