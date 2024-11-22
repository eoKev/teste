package com.example.musicwhisky1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TelaCadastro(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Texto indicando que é a tela de cadastro
        Text("Tela de Cadastro", modifier = Modifier.align(Alignment.CenterHorizontally))

        // Botão para voltar para a tela inicial
        Button(
            onClick = {
                navController.navigate("inicial") // Volta para a tela inicial
            },
            modifier = Modifier.align(Alignment.CenterHorizontally) // Centraliza o botão
        ) {
            Text("Voltar para Tela Inicial")
        }
    }
}