package com.example.musicwhisky1.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.musicwhisky1.dao.MusicaDao
import com.example.musicwhisky1.view.TelaInicial
import com.example.musicwhisky1.view.TelaGerenciamento
import com.example.musicwhisky1.viewmodel.AlbumVMFactory
import com.example.musicwhisky1.viewmodel.ArtistaVMFactory
import com.example.musicwhisky1.viewmodel.MusicaVMFactory
import com.example.mvvm2.model.database.AppDB
import com.example.mvvm2.viewmodel.AlbumVM
import com.example.mvvm2.viewmodel.ArtistaVM
import com.example.mvvm2.viewmodel.MusicaVM


@Composable
fun AppNavigation(
    navController: NavHostController,
    artistaDao: ArtistaDao,
    albumDao: AlbumDao,
    musicaDao: MusicaDao
) {
    NavHost(navController = navController, startDestination = "home") {

        // Tela inicial
        composable("home") {
            TelaInicial(navController)
        }

        // Tela de cadastro de artista
        composable("gerenciamentoArtista") {
            val artistaFactory = ArtistaVMFactory(artistaDao)
            val artistaVM: ArtistaVM = viewModel(factory = artistaFactory)
            TelaGerenciamentoArtista(navController, artistaVM)
        }

        // Tela de cadastro de álbum
        composable("gerenciamentoAlbum") {
            val albumFactory = AlbumVMFactory(albumDao, artistaDao)
            val albumVM: AlbumVM = viewModel(factory = albumFactory)

            // Instanciando o ArtistaVM diretamente
            val artistaFactory = ArtistaVMFactory(artistaDao)
            val artistaVM: ArtistaVM = viewModel(factory = artistaFactory)

            TelaGerenciamentoAlbum(navController, albumVM, artistaVM)
        }

        // Tela de gerenciamento geral
        composable("gerenciamento") {
            TelaGerenciamento(navController)
        }

        // Tela de cadastro de música
        composable("gerenciamentoMusica") {
            val albumFactory = AlbumVMFactory(albumDao, artistaDao)
            val albumVM: AlbumVM = viewModel(factory = albumFactory)

            val artistaFactory = ArtistaVMFactory(artistaDao)
            val artistaVM: ArtistaVM = viewModel(factory = artistaFactory)

            val musicaFactory = MusicaVMFactory(musicaDao, albumDao, artistaDao)
            val musicaVM: MusicaVM = viewModel(factory = musicaFactory)

            TelaGerenciamentoMusica(navController, musicaVM, artistaVM, albumVM)
        }
    }
}


