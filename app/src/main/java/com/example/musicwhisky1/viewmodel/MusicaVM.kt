package com.example.mvvm2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwhisky1.dao.MusicaDao
import com.example.musicwhisky1.model.Musica
import kotlinx.coroutines.launch

class MusicaVM(private val musicaDao: MusicaDao) : ViewModel() {

    var listaMusicas = mutableStateOf(listOf<Musica>())
        private set

    init {
        carregarMusicas()
    }

    private fun carregarMusicas() {
        viewModelScope.launch {
            listaMusicas.value = musicaDao.buscarTodos()
        }
    }

    fun salvarMusica(id: Int = 0, nome: String, idArtista: Int, duracao: String): String {
        if (nome.isBlank() || duracao.isBlank()) {
            return "Preencha todos os campos!"
        }

        val musica = Musica(id = 0, nome = nome, idArtista = idArtista, duracao = duracao )

        viewModelScope.launch {
            musicaDao.inserir(musica)
            carregarMusicas()
        }

        return "Música salva com sucesso!"
    }

    fun excluirMusica(artista: Musica) {
        viewModelScope.launch {
            musicaDao.deletar(artista)
            carregarMusicas()
        }
    }

    fun atualizarMusica(id: Int,  idArtista: Int, nome: String, duracao: String ): String {
        if (nome.isBlank() || duracao.isBlank()) {
            return "Preencha todos os campos!"
        }

        val musica = listaMusicas.value.find { it.id == id} ?: return "Erro ao atualizar música!"
        val artistaAtualizado = musica.copy(nome = nome, idArtista = idArtista, duracao = duracao)

        viewModelScope.launch {
            musicaDao.atualizar(artistaAtualizado)
            carregarMusicas()
        }

        return "Música atualizada com sucesso!"
    }
}
