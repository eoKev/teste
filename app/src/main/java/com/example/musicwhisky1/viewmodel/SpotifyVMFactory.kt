package com.example.musicwhisky1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SpotifyVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpotifyVM::class.java)) {
            return SpotifyVM() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
