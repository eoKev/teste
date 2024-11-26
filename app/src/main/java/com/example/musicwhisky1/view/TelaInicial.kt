package com.example.musicwhisky1.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicwhisky1.api.Artist
import com.example.musicwhisky1.api.Playlist
import com.example.musicwhisky1.api.SpotifyApiClient
import com.example.musicwhisky1.api.SpotifyAuth
import com.example.musicwhisky1.api.SpotifyService
import com.example.musicwhisky1.ui.SearchBar
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.musicwhisky1.ui.ArtistDialog
import com.example.musicwhisky1.viewmodel.SpotifyVM
import kotlinx.coroutines.delay

@Composable
fun TelaInicial(navController: NavController) {
    val spotifyVM: SpotifyVM = viewModel()

    val artists by spotifyVM.artists
    val playlists by spotifyVM.playlists
    val errorMessage by spotifyVM.errorMessage
    val isLoading by spotifyVM.isLoading

    var query by remember { mutableStateOf("") }
    var isInitialLoading by remember { mutableStateOf(true) }
    var isDialogOpen by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1500)

        spotifyVM.fetchAccessToken()
        if (spotifyVM._accessToken != null) {
            spotifyVM.fetchPlaylists()
        } else {
            spotifyVM._errorMessage.value = "Token de acesso não disponível"
        }

        isInitialLoading = false
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (isInitialLoading || isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {

            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = {
                    spotifyVM.searchArtists(it)
                    isDialogOpen = artists.isNotEmpty()
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Bem-vindo ao Musicwhisky!",
                color = Color(242, 5, 68),
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            errorMessage?.let {
                Text(text = "Erro: $it", color = Color.Red)
            }

            if (isDialogOpen) {
                ArtistDialog(artists = artists, onDismiss = { isDialogOpen = false })
            }

            if (playlists.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(playlists.chunked(2)) { playlistPair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            playlistPair.forEach { playlist ->
                                Column(
                                    modifier = Modifier
                                        .weight(1f)  // Garantir que cada item ocupe o mesmo espaço
                                        .clickable {
                                            navController.navigate("playlistTracks/${playlist.id}")
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    playlist.images.firstOrNull()?.let { image ->
                                        Image(
                                            painter = rememberImagePainter(image.url),
                                            contentDescription = "Imagem da playlist",
                                            modifier = Modifier.size(150.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = playlist.name,
                                        color = Color(254, 254, 228),
                                        fontSize = 18.sp, // Fonte maior
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navController.navigate("gerenciamento") }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Ir para gerenciamento")
            }
        }
    }
}

