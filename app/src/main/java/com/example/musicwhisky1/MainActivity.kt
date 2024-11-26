package com.example.musicwhisky1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.musicwhisky1.ui.theme.Musicwhisky1Theme
import com.example.musicwhisky1.view.AppNavigation
import com.example.musicwhisky1.viewmodel.SpotifyVM
import com.example.musicwhisky1.viewmodel.SpotifyVMFactory
import com.example.mvvm2.model.database.AppDB

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDB.getDatabase(applicationContext)
        val artistaDao = database.artistaDao()
        val albumDao = database.albumDao()
        val musicaDao = database.musicaDao()


        setContent {
            Musicwhisky1Theme(darkTheme = true) {

                val navController = rememberNavController()
                val spotifyVM: SpotifyVM = viewModel(factory = SpotifyVMFactory())

                AppNavigation(
                    navController = navController,
                    artistaDao = artistaDao,
                    albumDao = albumDao,
                    musicaDao = musicaDao,
                    spotifyVM = spotifyVM
                )
            }
        }
    }
}