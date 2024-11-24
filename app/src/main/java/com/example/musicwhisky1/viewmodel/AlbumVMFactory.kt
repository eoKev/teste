package com.example.musicwhisky1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.mvvm2.viewmodel.AlbumVM


class AlbumVMFactory(
    private val albumDao: AlbumDao,
    private val artistaDao: ArtistaDao // Adiciona o ArtistaDao aqui
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlbumVM(albumDao, artistaDao) as T // Passa o artistaDao ao criar o AlbumVM
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida!")
    }
}