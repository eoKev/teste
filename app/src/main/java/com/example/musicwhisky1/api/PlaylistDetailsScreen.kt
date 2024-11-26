package com.example.musicwhisky1.api

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistDetailsScreen(navController: NavController, playlistId: String) {
    var tracks by remember { mutableStateOf<List<Track>>(emptyList()) }
    var displayedTracks by remember { mutableStateOf<List<Track>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var isLoadingMore by remember { mutableStateOf(false) }
    var accessToken by remember { mutableStateOf<String?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentTrackId by remember { mutableStateOf<String?>(null) }
    var playlistName by remember { mutableStateOf<String?>(null) }
    val listState = rememberLazyListState()


    LaunchedEffect(playlistId) {
        try {
            accessToken = SpotifyAuth.getAccessToken()
        } catch (e: Exception) {
            errorMessage = "Erro ao obter token: ${e.message}"
            isLoading = false
            return@LaunchedEffect
        }

        accessToken?.let { token ->
            val api = SpotifyService.getSpotifyApi(token)
            val spotifyApiClient = SpotifyApiClient(api)

            spotifyApiClient.getPlaylistDetails(
                playlistId = playlistId,
                onResult = { playlist ->
                    playlist?.let {
                        tracks = it.tracks
                        displayedTracks = tracks.take(6)
                        playlistName = it.name
                    }
                    isLoading = false
                },
                onError = { error ->
                    errorMessage = error.message
                    isLoading = false
                }
            )
        } ?: run {
            errorMessage = "Token de acesso inválido ou expirado."
            isLoading = false
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        text = "Exibindo: ${playlistName ?: "Playlist"}",
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)
                    )
                },
                actions = {
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Voltar")
                    }
                }
            )

            errorMessage?.let {
                Text(text = "Erro: $it", color = Color.Red)
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                if (displayedTracks.isEmpty()) {
                    Text("Nenhuma música encontrada.")
                } else {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(displayedTracks) { track ->
                            val cardColor = MaterialTheme.colorScheme.surfaceVariant
                            val buttonColor = MaterialTheme.colorScheme.primary

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(containerColor = cardColor)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = track.name,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                    track.previewUrl?.let {
                                        Button(
                                            onClick = {
                                                if (currentTrackId == track.id && isPlaying) {
                                                    mediaPlayer?.pause() // Pausar a música
                                                    isPlaying = false
                                                } else {
                                                    mediaPlayer?.release()
                                                    mediaPlayer = MediaPlayer().apply {
                                                        CoroutineScope(Dispatchers.IO).launch {
                                                            try {
                                                                setDataSource(it)
                                                                prepareAsync()

                                                                setOnPreparedListener {
                                                                    CoroutineScope(Dispatchers.Main).launch {
                                                                        start()
                                                                        isPlaying = true
                                                                        currentTrackId = track.id
                                                                    }
                                                                }
                                                                setOnBufferingUpdateListener { _, percent ->
                                                                }

                                                                setOnCompletionListener {
                                                                    CoroutineScope(Dispatchers.Main).launch {
                                                                        isPlaying = false
                                                                        currentTrackId = null
                                                                    }
                                                                }
                                                            } catch (e: Exception) {
                                                            }
                                                        }
                                                    }
                                                }
                                            },
                                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                                        ) {
                                            Text(text = if (isPlaying && currentTrackId == track.id) "Pausar" else "Play")
                                        }
                                    }
                                }
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(56.dp))
                        }
                    }
                }
            }
        }

        if (!isLoadingMore && displayedTracks.size < tracks.size) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = {
                        isLoadingMore = true
                        CoroutineScope(Dispatchers.IO).launch {
                            delay(500)
                            val nextTracks = tracks.drop(displayedTracks.size).take(6)
                            withContext(Dispatchers.Main) {
                                displayedTracks = displayedTracks + nextTracks
                                isLoadingMore = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Carregar mais")
                }
            }
        }

        if (isLoadingMore) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}