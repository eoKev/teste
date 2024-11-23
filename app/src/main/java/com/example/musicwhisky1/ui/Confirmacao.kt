package com.example.musicwhisky1.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class DialogState(
    val title: String = "Aviso",
    val message: String = "",
    val onConfirm: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null,
    val okOnly: Boolean = false
)

@Composable
fun ConfirmDialog(dialogState: DialogState?, onDismissRequest: () -> Unit) {
    dialogState?.let { state ->
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = { Text(text = state.title) },
            text = { Text(text = state.message) },
            confirmButton = {
                Button(onClick = {
                    state.onConfirm?.invoke()
                    onDismissRequest()
                }) {
                    Text(text = if (state.okOnly) "OK" else "Confirmar")
                }
            },
            dismissButton = {
                if (!state.okOnly) {
                    Button(onClick = { onDismissRequest() }) {
                        Text(text = "Cancelar")
                    }
                }
            }
        )
    }
}
