package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    errorMessage: String = ""
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, color = Color.White) },
            leadingIcon = { Icon(icon, contentDescription = null, tint = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(start = 10.dp, top = 5.dp))
        }
    }
}