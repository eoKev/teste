package com.example.musicwhisky1.view

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicwhisky1.ui.*
import com.example.mvvm2.viewmodel.AlbumVM
import com.example.mvvm2.viewmodel.ArtistaVM

@Composable
fun TelaGerenciamentoAlbum(navController: NavController, albumVM: AlbumVM, artistaVM: ArtistaVM) {
    var nomeAlbum by remember { mutableStateOf("") }
    var quantidadeMusicas by remember { mutableStateOf("") }
    var dataLancamento by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var nomeArtista by remember { mutableStateOf("") }
    var dialogState by remember { mutableStateOf<DialogState?>(null) }
    var showArtistList by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val albums = albumVM.listar.value // Lista de álbuns

    // Layout da tela
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastrar Álbum",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Campos de entrada
        TextField(
            value = nomeAlbum,
            onValueChange = { nomeAlbum = it },
            label = { Text("Nome do Álbum") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = quantidadeMusicas,
            onValueChange = { quantidadeMusicas = it },
            label = { Text("Quantidade de Músicas") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = dataLancamento,
            onValueChange = { dataLancamento = it },
            label = { Text("Data de Lançamento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Gênero") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo para o nome do artista
        TextField(
            value = nomeArtista,
            onValueChange = { nomeArtista = it },
            label = { Text("Nome do Artista") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botões para salvar, atualizar e excluir
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    dialogState = DialogState(
                        title = "Confirmar Salvamento",
                        message = "Deseja salvar o álbum '$nomeAlbum'?",
                        onConfirm = {
                            val artista = artistaVM.buscarPorNome(nomeArtista)
                            if (artista != null) {
                                albumVM.salvar(
                                    nome = nomeAlbum,
                                    quantidadeMusicas = quantidadeMusicas.toIntOrNull() ?: 0,
                                    dataLancamento = dataLancamento,
                                    genero = genero,
                                    idArtista = artista.id,
                                    context = context
                                )
                                // Limpar os campos após salvar
                                nomeAlbum = ""
                                quantidadeMusicas = ""
                                dataLancamento = ""
                                genero = ""
                                nomeArtista = ""
                            } else {
                                Toast.makeText(context, "Artista não encontrado!", Toast.LENGTH_LONG).show()
                            }
                            dialogState = null
                        },
                        onDismiss = { dialogState = null },
                        okOnly = false
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Salvar")
            }

            Button(
                onClick = {
                    val album = albumVM.buscarPorNome(nomeAlbum)
                    if (album != null) {
                        dialogState = DialogState(
                            title = "Confirmar Atualização",
                            message = "Atualizar álbum '$nomeAlbum?'",
                            onConfirm = {
                                val artista = artistaVM.buscarPorNome(nomeArtista)
                                if (artista != null) {
                                    albumVM.atualizar(
                                        nome = nomeAlbum,
                                        quantidadeMusicas = quantidadeMusicas.toIntOrNull() ?: 0,
                                        dataLancamento = dataLancamento,
                                        genero = genero,
                                        idArtista = artista.id,
                                        context = context
                                    )
                                    // Limpar os campos após atualizar
                                    nomeAlbum = ""
                                    quantidadeMusicas = ""
                                    dataLancamento = ""
                                    genero = ""
                                    nomeArtista = ""
                                } else {
                                    Toast.makeText(context, "Artista não encontrado!", Toast.LENGTH_LONG).show()
                                }
                                dialogState = null
                            },
                            onDismiss = { dialogState = null },
                            okOnly = false
                        )
                    } else {
                        Toast.makeText(context, "Álbum não encontrado!", Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Atualizar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botões para listar e excluir álbuns
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { showArtistList = !showArtistList },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (showArtistList) "Ocultar" else "Listar")
            }

            Button(
                onClick = {
                    dialogState = DialogState(
                        title = "Confirmar Exclusão",
                        message = "Tem certeza que deseja excluir este álbum?",
                        onConfirm = {
                            albumVM.excluir(nomeAlbum, context)
                            // Limpar os campos após excluir
                            nomeAlbum = ""
                            quantidadeMusicas = ""
                            dataLancamento = ""
                            genero = ""
                            nomeArtista = ""
                            dialogState = null
                        },
                        onDismiss = { dialogState = null },
                        okOnly = false
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Excluir")
            }
        }

        // Mostrar a lista de álbuns
        if (showArtistList) {
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(albums.chunked(1)) { albumRow ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        albumRow.forEach { albums ->
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 80.dp)
                                    .padding(end = 80.dp)
                                    .clickable { /* Clique no artista */ },
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )
                            ) {
                                Column (

                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(10.dp),

                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ){
                                    val artistaNome =
                                        artistaVM.buscarPorId(albums.idArtista)?.nome
                                            ?: "Desconhecido"
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.headlineMedium.fontSize)) {
                                                append("${albums.nome}\n")
                                            }
                                            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize)) {
                                                append("Artista: $artistaNome")
                                            }
                                        },
                                        color = MaterialTheme.colorScheme.onTertiary,
                                        textAlign = TextAlign.Center, // Centraliza todo o texto dentro do componente
                                        modifier = Modifier.fillMaxWidth() // Garante que o texto ocupe toda a largura disponível
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Botão de voltar no rodapé
        Spacer(modifier = Modifier.weight(1f)) // Empurra os elementos para o topo
        Button(
            onClick = { navController.popBackStack() }, // Navega para a tela anterior
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Voltar")
        }
    }

    // Mostrar o diálogo de confirmação
    dialogState?.let {
        ConfirmDialog(dialogState = it, onDismissRequest = { dialogState = null })
    }
}