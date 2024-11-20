import androidx.lifecycle.LiveData
import com.example.app.entidades.AlbumMusicaCrossRef
import com.example.musicwhisky.Album
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.AlbumMusicaCrossRefDao
import com.example.musicwhisky1.model.AlbumComMusicas

class AlbumRepository(private val albumDao: AlbumDao) {

    // Inserir ou atualizar um álbum
    suspend fun inserirAlbum(album: Album) {
        albumDao.inserirAlbum(album)
    }

    // Atualizar um álbum
    suspend fun atualizarAlbum(album: Album) {
        albumDao.atualizarAlbum(album)
    }

    // Excluir um álbum
    suspend fun excluirAlbum(album: Album) {
        albumDao.deletarAlbum(album)
    }

    // Buscar álbum por ID
    fun buscarAlbumPorId(idAlbum: Int): LiveData<Album> {
        return albumDao.listarAlbumPorId(idAlbum)
    }

    // Listar todos os álbuns
    fun listarAlbuns(): LiveData<List<Album>> {
        return albumDao.listarAlbuns()
    }
}