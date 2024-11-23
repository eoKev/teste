package com.example.musicwhisky1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TelaCadastro(navController: NavController) {
    var selectedOption by remember { mutableStateOf("Artista") }

    // Estados para armazenar os dados do formulário
    var nome by remember { mutableStateOf("") }
    var quantidadeMusicas by remember { mutableStateOf("") }
    var duracao by remember { mutableStateOf("") }
    var dataLancamento by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    // Listas para dropdowns
    val artistas = listOf("Artista 1", "Artista 2", "Artista 3")
    val albuns = listOf("Álbum 1", "Álbum 2", "Álbum 3")

    var artistaSelecionado by remember { mutableStateOf("") }
    var albumSelecionado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastrar ${selectedOption}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Botões para selecionar o que cadastrar
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { selectedOption = "Artista" }) {
                Text("Artista")
            }
            Button(onClick = { selectedOption = "Álbum" }) {
                Text("Álbum")
            }
            Button(onClick = { selectedOption = "Música" }) {
                Text("Música")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Formulário dinâmico
        when (selectedOption) {
            "Artista" -> {
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome do Artista ou Banda") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            "Álbum" -> {
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
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
                // Dropdown para seleção do Artista
                DropdownMenuField(
                    label = "Artista",
                    items = artistas,
                    selectedItem = artistaSelecionado,
                    onItemSelected = { artistaSelecionado = it }
                )
            }
            "Música" -> {
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome da Música") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Dropdown para seleção do Artista
                DropdownMenuField(
                    label = "Artista",
                    items = artistas,
                    selectedItem = artistaSelecionado,
                    onItemSelected = { artistaSelecionado = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Dropdown para seleção do Álbum
                DropdownMenuField(
                    label = "Álbum",
                    items = albuns,
                    selectedItem = albumSelecionado,
                    onItemSelected = { albumSelecionado = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = duracao,
                    onValueChange = { duracao = it },
                    label = { Text("Duração Total") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de cadastro
        Button(
            onClick = {
                when (selectedOption) {
                    "Artista" -> {
                        println("Cadastrando Artista: Nome = $nome")
                    }
                    "Álbum" -> {
                        println("Cadastrando Álbum: Nome = $nome, Artista = $artistaSelecionado")
                    }
                    "Música" -> {
                        println("Cadastrando Música: Nome = $nome, Artista = $artistaSelecionado, Álbum = $albumSelecionado")
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cadastrar ${selectedOption}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para voltar à tela inicial
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Voltar para Tela Inicial")
        }
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = selectedItem,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaCadastro() {
    TelaCadastro(navController = rememberNavController())
}
