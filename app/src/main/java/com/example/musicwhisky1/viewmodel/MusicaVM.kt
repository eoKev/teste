package com.example.mvvm2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwhisky.Album
import com.example.musicwhisky1.dao.AlbumDao
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.musicwhisky1.dao.MusicaDao
import com.example.musicwhisky1.model.Musica
import kotlinx.coroutines.launch

class MusicaVM(
    private val musicaDao: MusicaDao,
    private val albumDao: AlbumDao,
    private val artistaDao: ArtistaDao// Adicionando a dependência do ArtistaVM para buscar o artista
) : ViewModel() {

    var listar = mutableStateOf(listOf<Musica>())
        private set

    init {
        carregar()
    }

    private fun carregar() {
        viewModelScope.launch {
            listar.value = musicaDao.buscarTodos()
        }
    }

    fun salvar(idAlbum : Int, idArtista: Int, nome: String, duracao: Int, context: Context) {

        if (nome.isBlank() || duracao <= 0) {
            exibirToast(context, "Preencha todos os campos!")
        }

        val album = albumDao.buscarPorId(idAlbum)
        if (album == null){
            exibirToast(context, "Álbum não encontrado! Verifique o nome")
            return
        }

        val artista = artistaDao.buscarPorId(idArtista)
        if (artista == null){
            exibirToast(context, ("Artista não encontrado! Verifique o nome"))
        }

        val musica = Musica(
            id = 0,
            idAlbum = idAlbum,
            idArtista = idArtista,
            nome = nome,
            duracao = duracao
        )



        viewModelScope.launch {
            musicaDao.inserir(musica)
            carregar()
            exibirToast(context, "Música salva com sucesso!")
        }


    }

    fun buscarPorNome(nome: String): Musica? {
        return listar.value.find { it.nome.equals(nome, ignoreCase = true) }
    }

    fun excluir(nome: String, context: Context) {

        val musica = buscarPorNome(nome)
        if (musica == null) {
            exibirToast(context, "Música não encontrada!")
            return
        }

        viewModelScope.launch {
            musicaDao.deletar(musica)
            carregar()
            exibirToast(context, "Música excluída com sucesso!")
        }
    }


    fun atualizar(idAlbum: Int, idArtista: Int, nome: String, duracao: Int, context: Context) {

        if (nome.isBlank() || duracao <= 0 || idArtista < 0 || idAlbum < 0) {
            exibirToast(context, "Preencha todos os campos corretamente!")
            return
        }
        val musica = buscarPorNome(nome)
        if (musica == null){
            exibirToast(context, "Música não encontrada!")
            return
        }

        val musicaAtualizada = musica.copy(idAlbum = idAlbum, idArtista = idArtista, nome = nome, duracao=duracao)

        viewModelScope.launch{
            musicaDao.atualizar(musicaAtualizada)
            carregar()
            exibirToast(context, "Música atualizada com sucesso!")
        }

    }

    private fun exibirToast(context: Context, mensagem: String) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()
    }

}







