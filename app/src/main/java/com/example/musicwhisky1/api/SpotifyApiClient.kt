package com.example.musicwhisky1.api

import SpotifyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import com.example.musicwhisky1.api.PlaylistsResponse

class SpotifyApiClient(private var api: SpotifyApi) {
    fun updateAccessToken(newToken: String) {
        api = SpotifyService.getSpotifyApi(newToken)
        Log.d("Spotify", "Token atualizado no SpotifyApiClient.")
    }

    private fun <T> handleResponse(
        call: Call<T>,
        onResult: (T?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e(
                        "Spotify",
                        "Erro na API: ${response.code()} - ${response.message()} | Body: $errorBody"
                    )
                    onError(Throwable("Erro ${response.code()}: ${response.message()}"))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.e("Spotify", "Falha na requisição: ${t.message}")
                onError(t)
            }
        })
    }

    fun searchArtist(
        query: String,
        onResult: (List<Artist>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = api.searchArtists(query)
        handleResponse(call, { response ->
            val artists = response?.artists?.items
            Log.d("Spotify", "Artistas encontrados: ${artists?.size}")
            onResult(artists)
        }, onError)
    }

    fun getArtistAlbums(
        artistId: String,
        onResult: (List<Album>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = api.getArtistAlbums(artistId)
        handleResponse(call, { response ->
            val albums = response?.items
            onResult(albums)
        }, onError)
    }

    fun getAlbumTracks(
        albumId: String,
        onResult: (List<Track>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = api.getAlbumTracks(albumId)
        handleResponse(call, { response: TracksResponse? ->
            val tracks = response?.items?.map { trackItem ->
                Track(
                    id = trackItem.track.id,
                    name = trackItem.track.name,
                    previewUrl = trackItem.track.preview_url
                )
            }
            onResult(tracks)
        }, onError)
    }

    fun getFeaturedPlaylists(
        onResult: (List<Playlist>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = api.getFeaturedPlaylists()
        handleResponse(call, { response ->
            val playlists = response?.playlists?.items
            onResult(playlists)
        }, onError)
    }

    fun getPlaylistDetails(
        playlistId: String,
        onResult: (Playlist?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = api.getPlaylistDetails(playlistId)
        handleResponse(call, { response ->
            val playlist = response?.toPlaylist()
            onResult(playlist)
        }, onError)
    }

    private fun PlaylistDetailsResponse.toPlaylist(): Playlist {
        return Playlist(
            id = this.id,
            name = this.name,
            description = this.description,
            images = this.images,
            tracks = this.tracks.items.map { trackItem ->
                Track(
                    id = trackItem.track.id,
                    name = trackItem.track.name,
                    previewUrl = trackItem.track.preview_url
                )
            }
        )
    }

    fun fetchPlaylistById(
        playlistId: String,
        onResult: (Playlist?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.getPlaylistDetails(playlistId).enqueue(object : Callback<PlaylistDetailsResponse> {
            override fun onResponse(
                call: Call<PlaylistDetailsResponse>,
                response: Response<PlaylistDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    val playlist = response.body()?.toPlaylist()
                    onResult(playlist)
                } else {
                    onError(Throwable("Erro ao buscar playlist: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<PlaylistDetailsResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}
