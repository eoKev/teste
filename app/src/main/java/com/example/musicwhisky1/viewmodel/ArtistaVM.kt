package com.example.mvvm2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwhisky1.dao.ArtistaDao
import com.example.musicwhisky1.model.Artista
import kotlinx.coroutines.launch

class ArtistaVM(private val artistaDao: ArtistaDao) : ViewModel() {

    var listar = mutableStateOf(listOf<Artista>())
        private set

    init {
        carregar()
    }

    private fun carregar() {
        viewModelScope.launch {
            listar.value = artistaDao.buscarTodos()
        }
    }

    fun salvar(nome: String, context: Context) {
        if (nome.isBlank()) {
            exibirToast(context, "Preencha todos os campos!")
            return
        }

        val artistaExistente = buscarPorNome(nome)
        if (artistaExistente != null) {
            exibirToast(context, "Já existe um artista cadastrado com este nome!")
            return
        }

        val novoArtista = Artista(id = 0, nome = nome)

        viewModelScope.launch {
            artistaDao.inserir(novoArtista)
            carregar()
            exibirToast(context, "Artista salvo com sucesso!")
        }
    }

    fun excluir(nome: String, context: Context) {
        val artista = buscarPorNome(nome)
        if (artista == null) {
            exibirToast(context, "Artista não encontrado!")
            return
        }

        viewModelScope.launch {
            artistaDao.deletar(artista)
            carregar()
            exibirToast(context, "Artista excluído com sucesso!")
        }
    }

    fun atualizar(id: Int, nome: String, context: Context) {
        if (nome.isBlank()) {
            exibirToast(context, "Preencha todos os campos!")
            return
        }

        val artistaExistente = buscarPorNome(nome)
        if (artistaExistente != null && artistaExistente.id != id) {
            exibirToast(context, "Já existe um artista com este nome!")
            return
        }

        val artista = listar.value.find { it.id == id }
        if (artista == null) {
            exibirToast(context, "Artista não encontrado para atualizar!")
            return
        }

        val artistaAtualizado = artista.copy(nome = nome)

        viewModelScope.launch {
            artistaDao.atualizar(artistaAtualizado)
            carregar()
            exibirToast(context, "Artista atualizado com sucesso!")
        }
    }

    fun buscarPorNome(nome: String): Artista? {
        return listar.value.find { it.nome.equals(nome, ignoreCase = true) }
    }

    private fun exibirToast(context: Context, mensagem: String) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()
    }
}
