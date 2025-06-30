package com.example.myapplication.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data_model.Car
@Composable
fun CarCard(car: Car, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Column(Modifier.padding(12.dp)) {
            Text("${car.brand} ${car.model}", style = MaterialTheme.typography.titleMedium)
            Text("Year: ${car.year}", style = MaterialTheme.typography.bodySmall)
        }
    }
}