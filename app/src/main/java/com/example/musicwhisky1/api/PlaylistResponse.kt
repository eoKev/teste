package com.example.musicwhisky1.api

data class PlaylistsResponse(
    val playlists: Playlists
)

data class Playlists(
    val items: List<Playlist>
)

data class Playlist(
    val id: String,
    val name: String,
    val description: String?,
    val images: List<Image>,
    val tracks: List<Track> = emptyList()
)

data class PlaylistDetailsResponse(
    val id: String,
    val name: String,
    val description: String?,
    val images: List<Image>,
    val tracks: TracksResponse
)


data class PlaylistTracks(
    val items: List<PlaylistTrackItem>
)

data class TrackDetails(
    val id: String,
    val name: String,
    val preview_url: String?
)


data class PlaylistTrackItem(
    val track: Track
)

data class Track(
    val id: String,
    val name: String,
    val previewUrl: String?
)

data class TracksResponse(
    val items: List<TrackItem>
)


data class TrackItem(
    val track: TrackDetails
)


data class AlbumDetails(
    val id: String,
    val name: String
)

data class Image(
    val url: String,
    val height: Int?,
    val width: Int?
)
