package com.example.musicwhisky1.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = {
                    Text(
                        text = "Pesquisar...",
                        fontSize = 12.sp,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pesquisar",
                        modifier = Modifier.size(14.dp)
                    )
                },
                shape = RoundedCornerShape(15.dp),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                ),
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    SearchBar(
        query = query,
        onQueryChange = { newQuery -> query = newQuery }
    )
}
