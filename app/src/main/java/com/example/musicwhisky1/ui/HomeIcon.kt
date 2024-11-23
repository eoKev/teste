package com.example.musicwhisky1.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

@Composable
fun HomeIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Int = 24 // Tamanho do ícone de home
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.padding(top = 22.dp,start = 16.dp),
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "Home",
            tint = Color(red = 242, green = 5, blue = 92),
            modifier = Modifier.size(iconSize.dp) // Definindo o tamanho do ícone
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeIconPreview() {
    HomeIcon(onClick = { /* Ação ao clicar */ })
}
