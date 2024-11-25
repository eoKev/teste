package com.example.musicwhisky1.view
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicwhisky1.model.Artista
import com.example.musicwhisky1.model.Musica
import com.example.musicwhisky1.ui.ConfirmDialog
import com.example.musicwhisky1.ui.DialogState
import com.example.mvvm2.viewmodel.AlbumVM
import com.example.mvvm2.viewmodel.ArtistaVM
import com.example.mvvm2.viewmodel.MusicaVM
import kotlinx.coroutines.launch

@Composable
fun TelaGerenciamentoMusica(
    navController: NavController,
    musicaVM: MusicaVM,
    artistaVM: ArtistaVM,
    albumVM: AlbumVM
) {
    var nomeMusica by remember { mutableStateOf("") }
    var nomeArtista by remember { mutableStateOf("")}
    var nomeAlbum by remember { mutableStateOf("")}
    var duracao by remember { mutableStateOf("") }
    var dialogState by remember { mutableStateOf<DialogState?>(null) }
    var showArtistList by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val artistasList = artistaVM.listar.value // Lista de artistas
    val albunsList = albumVM.listar.value // Lista de álbuns
    val musicaList = musicaVM.listar.value



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastrar Música",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Campo para o nome da música
        TextField(
            value = nomeMusica,
            onValueChange = { nomeMusica = it },
            label = { Text("Nome da Música") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para artista
        TextField(
            value = nomeArtista,
            onValueChange = { nomeArtista = it },
            label = { Text("Artista") },
            modifier = Modifier.fillMaxWidth()

        )


        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para álbum (opcional)
        TextField(
            value = nomeAlbum,
            onValueChange = { nomeAlbum = it },
            label = { Text("Álbum") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para duração
        TextField(
            value = duracao,
            onValueChange = { duracao = it },
            label = { Text("Duração Total (em segundos)") },
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
                        message = "Deseja salva a música '$nomeMusica'?",
                        onConfirm = {
                            val artista = artistaVM.buscarPorNome(nomeArtista)
                            val album = albumVM.buscarPorNome(nomeAlbum)

                            if (artista != null && album != null){
                                musicaVM.salvar(
                                    idArtista = artista.id,
                                    idAlbum = album.id,
                                    nome = nomeMusica,
                                    duracao = duracao.toInt(),
                                    context = context
                                )

                                nomeArtista = ""
                                nomeAlbum = ""
                                nomeMusica = ""
                                duracao = ""
                            } else {
                                Toast.makeText(context, "Artista ou Álbum inválidos!", Toast.LENGTH_LONG).show()
                            }
                            dialogState = null
                        },
                        onDismiss = { dialogState = null},
                        okOnly = false
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Salvar Música")
            }

            Button(
                onClick = {
                   val musica = musicaVM.buscarPorNome(nomeMusica)
                    if (musica != null){
                        dialogState = DialogState(
                            title = "Confirmar Atualização",
                            message = "Atualizar música '$nomeMusica'?",
                            onConfirm = {
                                val album = albumVM.buscarPorNome(nomeAlbum)
                                val artista = artistaVM.buscarPorNome(nomeArtista)
                                if (artista!=null && album!= null){
                                    musicaVM.atualizar(
                                        idArtista = artista.id,
                                        idAlbum = album.id,
                                        nome = nomeMusica,
                                        duracao = duracao.toInt(),
                                        context = context
                                    )
                                    nomeArtista = ""
                                    nomeAlbum = ""
                                    nomeMusica = ""
                                    duracao = ""
                                } else {
                                    Toast.makeText(context, "Artista ou Álbum não encontrados!", Toast.LENGTH_LONG).show()
                                }
                                dialogState = null
                            },
                            onDismiss = { dialogState = null },
                            okOnly = false
                        )
                    } else {
                        Toast.makeText(context, "Música não encontrada!", Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Atualizar Música")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    showArtistList = !showArtistList
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(if (showArtistList) "Ocultar" else "Listar")
            }

            Button(
                onClick = {
                    dialogState = DialogState(
                        title = "Confirmar Exclusão",
                        message = "Tem certeza que deseja excluir a música?",
                        onConfirm = {
                            musicaVM.excluir(nomeMusica, context)
                            nomeMusica = "" // Limpar o campo após excluir
                            nomeArtista = ""
                            nomeAlbum = ""
                            duracao = ""
                            dialogState = null
                        },
                        onDismiss = { dialogState = null },
                        okOnly = false
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Excluir Música")
            }
        }

        // Exibe a lista de artistas se a flag showArtistList for verdadeira
        if (showArtistList) {
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(musicaList.chunked(1)) { musicRow ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        musicRow.forEach { musica ->
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
                                    val artistaNome =
                                        artistaVM.buscarPorId(musica.idArtista)?.nome
                                            ?: "Desconhecido"
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.headlineMedium.fontSize)) {
                                                append("${musica.nome}\n")
                                            }
                                            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize)) {
                                                append(artistaNome)
                                            }
                                        },
                                        color = MaterialTheme.colorScheme.onTertiary,
                                         // Centraliza todo o texto dentro do componente
                                        modifier = Modifier.fillMaxWidth() // Garante que o texto ocupe toda a largura disponível
                                    )
                               }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f)) // Empurra os elementos para o topo
        Button(
            onClick = { navController.popBackStack() }, // Navega para a tela anterior
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Voltar")
        }
    }

    // Exibe o diálogo de confirmação, se necessário
    dialogState?.let {
        ConfirmDialog(dialogState = it, onDismissRequest = { dialogState = null })
    }
}