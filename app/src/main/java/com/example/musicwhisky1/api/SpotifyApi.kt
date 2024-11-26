
import com.example.musicwhisky1.api.AlbumsResponse
import com.example.musicwhisky1.api.PlaylistDetailsResponse
import com.example.musicwhisky1.api.PlaylistsResponse
import com.example.musicwhisky1.api.SearchResponse
import com.example.musicwhisky1.api.TracksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApi {

    @GET("search")
    fun searchArtists(
        @Query("q") query: String,
        @Query("type") type: String = "artist"
    ): Call<SearchResponse>

    @GET("artists/{id}/albums")
    fun getArtistAlbums(@Path("id") artistId: String): Call<AlbumsResponse>

    @GET("albums/{id}/tracks")
    fun getAlbumTracks(@Path("id") albumId: String): Call<TracksResponse>

    @GET("v1/browse/featured-playlists")
    fun getFeaturedPlaylists(): Call<PlaylistsResponse>

    @GET("playlists/{playlist_id}")
    fun getPlaylistDetails(
        @Path("playlist_id") playlistId: String
    ): Call<PlaylistDetailsResponse>
}