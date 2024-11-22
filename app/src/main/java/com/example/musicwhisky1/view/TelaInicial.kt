package com.example.musicwhisky1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicwhisky1.ui.SearchBar

@Composable
fun TelaInicial(navController: NavController) {

    var query by remember { mutableStateOf("") }


    SearchBar(
        query = query,
        onQueryChange = { newQuery -> query = newQuery }
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {

        Button(
            onClick = {
                navController.navigate("cadastro")
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp),
        ) {
            Text("Ir para Cadastro")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaInicial() {
    TelaInicial(navController = rememberNavController())
}
