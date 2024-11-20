package com.example.musicwhisky1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwhisky1.dao.MusicaDao
import com.example.mvvm2.viewmodel.MusicaVM


class MusicaVMFactory (
    private val musicaDao: MusicaDao
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MusicaVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MusicaVM(musicaDao) as T
            }
            throw IllegalArgumentException("Classe ViewModel desconhecida")
        }
}