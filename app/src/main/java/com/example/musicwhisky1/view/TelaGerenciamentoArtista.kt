package com.example.musicwhisky1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicwhisky1.ui.ConfirmDialog
import com.example.musicwhisky1.ui.DialogState
import com.example.mvvm2.viewmodel.ArtistaVM

@Composable
fun TelaGerenciamentoArtista(navController: NavController, artistaVM: ArtistaVM) {
    var nomeArtista by remember { mutableStateOf("") }
    var nomeArtistaNovo by remember { mutableStateOf("") }
    var dialogState by remember { mutableStateOf<DialogState?>(null) }
    val context = LocalContext.current
    var showUpdateField by remember { mutableStateOf(false) }
    var showArtistList by remember { mutableStateOf(false) }
    val artists = artistaVM.listar.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gerenciamento de Artistas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        TextField(
            value = nomeArtista,
            onValueChange = { nomeArtista = it },
            label = { Text("Nome do Artista ou Banda") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (nomeArtista.isNotBlank()) {
                        val artistaExistente = artistaVM.buscarPorNome(nomeArtista)
                        if (artistaExistente != null) {
                            dialogState = DialogState(
                                title = "Aviso",
                                message = "Já existe um artista cadastrado com este nome!",
                                onConfirm = { dialogState = null },
                                onDismiss = { dialogState = null },
                                okOnly = true
                            )
                        } else {
                            dialogState = DialogState(
                                title = "Confirmar Salvamento",
                                message = "Tem certeza que deseja salvar este artista?",
                                onConfirm = {
                                    artistaVM.salvar(nomeArtista, context)
                                    dialogState = null
                                    nomeArtista = ""
                                },
                                onDismiss = { dialogState = null },
                                okOnly = false
                            )
                        }
                    } else {
                        dialogState = DialogState(
                            title = "Aviso",
                            message = "Digite o nome do artista para salvar!",
                            onConfirm = { dialogState = null },
                            onDismiss = { dialogState = null },
                            okOnly = true
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Salvar Artista")
            }

            Button(
                onClick = {
                    if (nomeArtista.isNotBlank()) {
                        val artista = artistaVM.buscarPorNome(nomeArtista)
                        if (artista != null) {
                            showUpdateField = true
                        } else {
                            dialogState = DialogState(
                                title = "Aviso",
                                message = "Artista não encontrado para atualização!",
                                onConfirm = { dialogState = null },
                                onDismiss = { dialogState = null },
                                okOnly = true
                            )
                        }
                    } else {
                        dialogState = DialogState(
                            title = "Aviso",
                            message = "Digite o nome do artista para atualizar!",
                            onConfirm = { dialogState = null },
                            onDismiss = { dialogState = null },
                            okOnly = true
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Atualizar")
            }
        }

        if (showUpdateField) {
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = nomeArtistaNovo,
                onValueChange = { nomeArtistaNovo = it },
                label = { Text("Novo nome do artista") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nomeArtistaNovo.isNotBlank()) {
                        artistaVM.atualizar(
                            nome = nomeArtista,
                            nomeNovo = nomeArtistaNovo,
                            context = context
                        )
                        showUpdateField = false
                        dialogState = null
                    } else {
                        dialogState = DialogState(
                            title = "Aviso",
                            message = "Por favor, forneça um novo nome para o artista.",
                            onConfirm = { dialogState = null },
                            onDismiss = { dialogState = null },
                            okOnly = true
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Confirmar Atualização")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { showArtistList = !showArtistList },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(if (showArtistList) "Ocultar Artistas" else "Listar Artistas")
            }

            Button(
                onClick = {
                    if (nomeArtista.isNotBlank()) {
                        val artista = artistaVM.buscarPorNome(nomeArtista)
                        if (artista != null) {
                            dialogState = DialogState(
                                title = "Confirmar exclusão",
                                message = "Tem certeza que deseja excluir este artista?",
                                onConfirm = {
                                    artistaVM.excluir(nomeArtista, context)
                                    dialogState = null
                                    nomeArtista = ""
                                },
                                onDismiss = { dialogState = null },
                                okOnly = false
                            )
                        } else {
                            dialogState = DialogState(
                                title = "Aviso",
                                message = "Artista não encontrado!",
                                onConfirm = { dialogState = null },
                                onDismiss = { dialogState = null },
                                okOnly = true
                            )
                        }
                    } else {
                        dialogState = DialogState(
                            title = "Aviso",
                            message = "Digite o nome do artista para excluir!",
                            onConfirm = { dialogState = null },
                            onDismiss = { dialogState = null },
                            okOnly = true
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Excluir Artista")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showArtistList) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(artists.chunked(1)) { artistRow ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        artistRow.forEach { artista ->
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 80.dp)
                                    .padding(end = 80.dp)
                                    .clickable { /* Clique no artista */ },
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background)
                            ) {
                                Column(

                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(16.dp),

                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = artista.nome,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onTertiary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Voltar")
        }
    }

    dialogState?.let {
        ConfirmDialog(dialogState = it, onDismissRequest = { dialogState = null })
    }
}


