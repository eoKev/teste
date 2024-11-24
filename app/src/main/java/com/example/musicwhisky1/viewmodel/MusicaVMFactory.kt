package com.example.musicwhisky1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.musicwhisky1.dao.MusicaDao
import com.example.mvvm2.viewmodel.AlbumVM
import com.example.mvvm2.viewmodel.ArtistaVM
import com.example.mvvm2.viewmodel.MusicaVM

class MusicaVMFactory(
    private val musicaDao: MusicaDao,
    private val albumDao : AlbumDao,
    private val artistaDao: ArtistaDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicaVM::class.java)) {
            return MusicaVM(musicaDao, albumDao, artistaDao) as T
        }
        throw IllegalArgumentException("Classe desconhecida")
    }
}