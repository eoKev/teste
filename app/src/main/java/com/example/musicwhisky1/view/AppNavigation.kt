package com.example.musicwhisky1.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicwhisky1.view.TelaInicial
import com.example.musicwhisky1.view.TelaCadastro

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { TelaInicial(navController) }
        composable("cadastro") { TelaCadastro(navController) }
    }
}