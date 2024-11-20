package com.example.musicwhisky1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwhisky.model.ArtistaDao
import com.example.mvvm2.viewmodel.ArtistaVM

class ArtistaVMFactory(private val artistaDao: ArtistaDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistaVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtistaVM(artistaDao) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }

}
