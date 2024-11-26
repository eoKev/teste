package com.example.mvvm2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwhisky.Album
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.musicwhisky1.model.Artista
import kotlinx.coroutines.launch

class AlbumVM(private val albumDao: AlbumDao, private val artistaDao: ArtistaDao) : ViewModel() {

    var listar = mutableStateOf(listOf<Album>())
        private set

    init {
        carregar()
    }

    private fun carregar() {
        viewModelScope.launch {
            listar.value = albumDao.buscarTodos()
        }
    }

    fun salvar(nome: String, quantidadeMusicas: Int, dataLancamento: String,
        genero: String, idArtista: Int, context: Context
    ){

        if (nome.isBlank() || dataLancamento.isBlank() || genero.isBlank() || quantidadeMusicas <= 0 || idArtista <= 0) {
            exibirToast(context, "Preencha todos os campos corretamente!")
            return
        }
        val albumExistente = buscarPorNome(nome)
        if (albumExistente != null) {
            exibirToast(context,"Álbum já cadastrado!" )
            return
        }

        val artista = artistaDao.buscarPorId(idArtista) // Método para buscar por ID
        if (artista == null) {
            exibirToast(context,"Artista não encontrado! Verifique o nome")
            return
        }

        val album = Album(
            id = 0,
            nome = nome,
            quantidadeMusicas = quantidadeMusicas,
            dataLancamento = dataLancamento,
            genero = genero,
            idArtista = idArtista
        )

        viewModelScope.launch {
            albumDao.inserir(album)
            carregar()
            exibirToast(context,"Álbum salvo com sucesso!")
        }
    }

    fun atualizar(nome: String, quantidadeMusicas: Int, dataLancamento: String, genero: String, idArtista: Int, context: Context) {

        if (nome.isBlank() || quantidadeMusicas < 0 || dataLancamento.isBlank() || genero.isBlank() || idArtista < 0) {
            exibirToast(context, "Preencha todos os campos!")
            return
        }

        val artista = artistaDao.buscarPorId(idArtista) // Método para buscar por ID
        if (artista == null) {
            exibirToast(context,"Artista não encontrado! Verifique o nome")
            return
        }

        val album = buscarPorNome(nome)
        if (album == null){
            exibirToast(context, "Álbum não encontrado, verifique o nome!")
            return
        }

        val albumAtualizado = album.copy(nome = nome, quantidadeMusicas = quantidadeMusicas, dataLancamento = dataLancamento, genero = genero, idArtista = idArtista)


        viewModelScope.launch {
            albumDao.atualizar(albumAtualizado)
            carregar()
            exibirToast(context, "Álbum atualizado com sucesso!")
        }

    }

    fun excluir(nome: String, context: Context) {

        val album = buscarPorNome(nome)
        if (album == null) {
            exibirToast(context, "Álbum não encontrado!")
            return
        }

        viewModelScope.launch {
            albumDao.deletar(album)
            carregar()
            exibirToast(context, "Álbum excluído com sucesso!")
        }
    }

    fun buscarPorId(id: Int): Album? {
        return listar.value.find { it.id == id }
    }

    fun buscarPorNome(nome: String): Album? {
        return listar.value.find { it.nome.equals(nome, ignoreCase = true) }
    }

    private fun exibirToast(context: Context, mensagem: String) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()
    }


}