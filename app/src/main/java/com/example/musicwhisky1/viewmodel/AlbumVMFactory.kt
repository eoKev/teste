package com.example.musicwhisky1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwhisky1.model.AlbumDao
import com.example.mvvm2.viewmodel.AlbumVM


class AlbumVMFactory (private val albumDao: AlbumDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlbumVM(albumDao) as T
        }
        throw IllegalArgumentException("Classe Viewmodel desconhecida!")
    }


}

