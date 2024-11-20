package com.example.mvvm2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwhisky.Album
import com.example.musicwhisky.model.ArtistaDao
import com.example.musicwhisky1.model.AlbumDao
import kotlinx.coroutines.launch

class AlbumVM(private val albumDao: AlbumDao, private val artistaDao: ArtistaDao) : ViewModel() {

    var listaAlbuns = mutableStateOf(listOf<Album>())
        private set

    init {
        carregarAlbuns()
    }

    private fun carregarAlbuns() {
        viewModelScope.launch {
            listaAlbuns.value = albumDao.buscarTodos()
        }
    }

    fun salvarAlbum(nomeAlbum: String, quantidadeMusicas: Int, duracao: String, dataLancamento: String, genero: String, idArtista: Int): String {
        if (nomeAlbum.isBlank() || duracao.isBlank() || dataLancamento.isBlank() || genero.isBlank()) {
            return "Preencha todos os campos!"
        }

        val album = Album(id = 0, nome = nomeAlbum, quantidadeMusicas = quantidadeMusicas, duracao = duracao, dataLancamento = dataLancamento, genero = genero, idArtista = idArtista
        )
        viewModelScope.launch {
            albumDao.inserir(album)
            carregarAlbuns()

        }
        return "Álbum salvo com sucesso!"
    }

    fun excluirAlbum(album: Album) {
        viewModelScope.launch {
            albumDao.deletar(album)
            carregarAlbuns()
        }
    }

    fun atualizarAlbum(id: Int, nome: String, quantidadeMusicas: Int, duracao: String, dataLancamento: String, genero: String, idArtista: Int): String {
        if (nome.isBlank() || quantidadeMusicas < 0 || duracao.isBlank() || dataLancamento.isBlank() || genero.isBlank() || idArtista < 0) {
            return "Preencha todos os campos!"
        }

        val album = listaAlbuns.value.find { it.id == id } ?: return "Erro ao atualizar álbum!"
        val albumAtualizado = album.copy(nome = nome, quantidadeMusicas = quantidadeMusicas, duracao = duracao, dataLancamento = dataLancamento, genero = genero, idArtista = idArtista)

        viewModelScope.launch {
            albumDao.atualizar(albumAtualizado)
            carregarAlbuns()
        }

        return "Álbum atualizado com sucesso!"
    }
}
