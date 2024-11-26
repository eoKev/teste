package com.example.musicwhisky1.api

data class AlbumsResponse(
    val items: List<Album>
)

data class Album(
    val id: String,
    val name: String
)