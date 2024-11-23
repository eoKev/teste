package com.example.musicwhisky1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
fun TelaCadastroArtista(navController: NavController, artistaVM: ArtistaVM) {
    var nomeArtista by remember { mutableStateOf("") }
    var dialogState by remember { mutableStateOf<DialogState?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastrar Artista",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
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
            // Botão para excluir artista
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (nomeArtista.isNotBlank()) {
                        val artista = artistaVM.buscarPorNome(nomeArtista)
                        if (artista != null) {
                            dialogState = DialogState(
                                title = "Confirmar exclusão",
                                message = "Tem certeza que deseja excluir este artista?",
                                onConfirm = {
                                    artistaVM.excluir(nomeArtista, context)
                                    dialogState = null // Close dialog after action
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
                }
            ) {
                Text("Excluir Artista")
            }

            // Botão para salvar o artista
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
                                    dialogState = null // Close dialog after action
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
                }
            ) {
                Text("Salvar Artista")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para voltar à tela anterior
        Button(
            onClick = { navController.popBackStack() }, // Voltar à tela anterior
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }

    // Show the confirmation or warning dialog if needed
    dialogState?.let {
        ConfirmDialog(dialogState = it, onDismissRequest = { dialogState = null })
    }
}

