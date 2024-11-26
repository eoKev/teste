package com.example.musicwhisky1.api

data class SearchResponse(
    val artists: Artists
)

data class Artists(
    val items: List<Artist>
)

data class Artist(
    val id: String,
    val name: String
)