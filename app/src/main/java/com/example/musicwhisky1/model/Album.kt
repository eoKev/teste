package com.example.musicwhisky
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.musicwhisky1.model.Artista

@Entity(tableName = "albuns",
    foreignKeys = [
        ForeignKey(
            entity = Artista::class,
            parentColumns = ["id"],
            childColumns = ["idArtista"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["idArtista"])]
)
data class Album(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val quantidadeMusicas: Int = 0,
    val duracao: String,
    val dataLancamento: String,
    val genero: String,
    val idArtista: Int
)