package com.example.musicwhisky1.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.musicwhisky1.api.PlaylistDetailsScreen
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.musicwhisky1.dao.MusicaDao
import com.example.musicwhisky1.view.TelaGerenciamento
import com.example.musicwhisky1.viewmodel.AlbumVMFactory
import com.example.musicwhisky1.viewmodel.ArtistaVMFactory
import com.example.musicwhisky1.viewmodel.MusicaVMFactory
import com.example.musicwhisky1.viewmodel.SpotifyVM
import com.example.mvvm2.model.database.AppDB
import com.example.mvvm2.viewmodel.AlbumVM
import com.example.mvvm2.viewmodel.ArtistaVM
import com.example.mvvm2.viewmodel.MusicaVM

@Composable
fun AppNavigation(
    navController: NavHostController,
    artistaDao: ArtistaDao,
    albumDao: AlbumDao,
    musicaDao: MusicaDao,
    spotifyVM: SpotifyVM
) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            TelaInicial(navController)
        }

        composable("gerenciamento") {
            TelaGerenciamento(navController)
        }

        composable("gerenciamentoArtista") {
            val artistaVM: ArtistaVM = viewModel(factory = ArtistaVMFactory(artistaDao))
            TelaGerenciamentoArtista(navController, artistaVM)
        }

        composable("gerenciamentoAlbum") {
            val artistaVM: ArtistaVM = viewModel(factory = ArtistaVMFactory(artistaDao))
            val albumVM: AlbumVM = viewModel(factory = AlbumVMFactory(albumDao, artistaDao))
            TelaGerenciamentoAlbum(navController, albumVM, artistaVM)
        }

        composable("gerenciamentoMusica") {

            val artistaVM: ArtistaVM = viewModel(factory = ArtistaVMFactory(artistaDao))
            val albumVM: AlbumVM = viewModel(factory = AlbumVMFactory(albumDao, artistaDao))
            val musicaVM: MusicaVM = viewModel(factory = MusicaVMFactory(musicaDao, albumDao, artistaDao))
            TelaGerenciamentoMusica(navController, musicaVM, artistaVM, albumVM)
        }

        composable(
            route = "playlistTracks/{playlistId}",
            arguments = listOf(navArgument("playlistId") { type = NavType.StringType })
        ) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getString("playlistId").orEmpty()
            PlaylistDetailsScreen(navController, playlistId)
        }
    }
}


