package com.example.mvvm2.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.musicwhisky.Album
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.musicwhisky1.dao.MusicaDao
import com.example.musicwhisky1.model.Musica
import com.example.musicwhisky1.model.Artista

@Database(entities = [Artista::class, Musica::class, Album::class], version = 3)
abstract class AppDB : RoomDatabase() {

    abstract fun artistaDao(): ArtistaDao
    abstract fun musicaDao(): MusicaDao
    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "musicwhisky.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}