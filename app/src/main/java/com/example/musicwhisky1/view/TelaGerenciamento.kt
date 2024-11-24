package com.example.musicwhisky1.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.musicwhisky1.ui.HomeIcon
import com.example.musicwhisky1.ui.SearchBar

@Composable
fun TelaGerenciamento(navController: NavController) {
    var query by remember { mutableStateOf("") }

    HomeIcon(onClick = { navController.navigate("home") }, iconSize = 32)

    SearchBar(
        query = query,
        onQueryChange = { newQuery -> query = newQuery }
    )

    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre a barra de pesquisa e os botões


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("gerenciamentoArtista") },
            modifier = Modifier
                .fillMaxWidth(0.8f) // Botão ocupa 80% da largura
                .padding(vertical = 8.dp) // Espaçamento vertical entre botões
        ) {
            Text("Artista")
        }
        Button(
            onClick = { navController.navigate("gerenciamentoAlbum") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp)
        ) {
            Text("Álbum")
        }
        Button(
            onClick = { navController.navigate("gerenciamentoMusica") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp)
        ) {
            Text("Música")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaCadastro() {
    TelaGerenciamento(navController = rememberNavController())
}