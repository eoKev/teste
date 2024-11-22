package com.example.musicwhisky1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.musicwhisky1.ui.theme.Musicwhisky1Theme
import com.example.musicwhisky1.view.TelaInicial

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Musicwhisky1Theme(darkTheme = true) {

                val navController = rememberNavController()

                // Exibe diretamente a TelaInicial
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    TelaInicial(navController = navController)
                }
            }
        }
    }
}
