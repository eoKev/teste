package com.example.mvvm2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwhisky.model.ArtistaDao
import com.example.musicwhisky1.model.Artista
import kotlinx.coroutines.launch

class ArtistaVM(private val artistaDao: ArtistaDao) : ViewModel() {

    var listaArtistas = mutableStateOf(listOf<Artista>())
        private set

    init {
        carregarArtistas()
    }

    private fun carregarArtistas() {
        viewModelScope.launch {
            listaArtistas.value = artistaDao.buscarTodos()
        }
    }

    fun salvarArtista(nome: String): String {
        if (nome.isBlank()) {
            return "Preencha todos os campos!"
        }

        val artista = Artista(id = 0, nome = nome)

        viewModelScope.launch {
            artistaDao.inserir(artista)
            carregarArtistas()
        }

        return "Artista salvo com sucesso!"
    }

    fun excluirArtista(artista: Artista) {
        viewModelScope.launch {
            artistaDao.deletar(artista)
            carregarArtistas()
        }
    }

    fun atualizarArtista(id: Int, nome: String): String {
        if (nome.isBlank()) {
            return "Preencha todos os campos!"
        }

        val artista = listaArtistas.value.find { it.id == id } ?: return "Erro ao atualizar artista"
        val artistaAtualizado = artista.copy(nome = nome)

        viewModelScope.launch {
            artistaDao.atualizar(artistaAtualizado)
            carregarArtistas()
        }

        return "Artista atualizado com sucesso!"
    }
}
