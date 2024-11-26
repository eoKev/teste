package com.example.musicwhisky1.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwhisky1.api.*
import kotlinx.coroutines.launch


class SpotifyVM : ViewModel() {

    private var _artists = mutableStateOf<List<Artist>>(emptyList())
    val artists: State<List<Artist>> = _artists

    private var _playlists = mutableStateOf<List<Playlist>>(emptyList())
    val playlists: State<List<Playlist>> = _playlists

    var _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    var _accessToken: String? = null
    private var _playlistsLoaded = mutableStateOf(false)

    suspend fun fetchAccessToken() {
        if (_accessToken == null) {
            _accessToken = SpotifyAuth.getAccessToken() // Obter o token de acesso
        }
    }

    fun fetchPlaylists() {
        if (_accessToken == null) {
            _errorMessage.value = "Token de acesso não disponível"
            return
        }

        if (_playlistsLoaded.value) {
            return
        }

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val api = SpotifyService.getSpotifyApi(_accessToken!!)
                val spotifyApiClient = SpotifyApiClient(api)

                val playlistIds = listOf(
                    "37i9dQZF1DWXRqgorJj26U",
                    "37i9dQZF1DX0Yxoavh5qJV",
                    "37i9dQZF1DWXx6OpWBkqVv",
                    "4o5malfpzbr6qG1aHryfil"
                )

                val playlistsFetched = mutableListOf<Playlist>()
                playlistIds.forEach { id ->
                    spotifyApiClient.fetchPlaylistById(
                        playlistId = id,
                        onResult = { playlist ->
                            playlist?.let {
                                playlistsFetched.add(it)
                                _playlists.value = playlistsFetched
                            }
                        },
                        onError = { error ->
                            _errorMessage.value = error.message
                        }
                    )
                }

                _playlistsLoaded.value = true
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchArtists(query: String) {
        if (_accessToken == null) {
            _errorMessage.value = "Token de acesso não disponível"
            return
        }

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val api = SpotifyService.getSpotifyApi(_accessToken!!)
                val spotifyApiClient = SpotifyApiClient(api)

                spotifyApiClient.searchArtist(query, onResult = { artistList ->
                    _artists.value = artistList ?: emptyList()
                }, onError = { error ->
                    _errorMessage.value = error.message
                })
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}
