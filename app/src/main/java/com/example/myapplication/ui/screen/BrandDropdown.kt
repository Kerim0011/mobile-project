package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
@Composable
fun DropdownMenuBox(brands: List<String?>, selectedBrand: String?, onBrandSelected: (String?) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedBrand ?: "All Brands")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            brands.forEach { brand ->
                DropdownMenuItem(
                    text = { Text(brand ?: "All") },
                    onClick = {
                        onBrandSelected(brand)
                        expanded = false
                    }
                )
            }
        }
    }
}