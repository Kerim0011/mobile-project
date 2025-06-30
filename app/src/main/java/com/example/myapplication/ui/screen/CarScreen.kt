package com.example.myapplication.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data_model.Car
import com.example.myapplication.data_model.Customer
import com.example.myapplication.data_model.Rental
import com.example.myapplication.viewmodel.CarViewModel
import com.example.myapplication.viewmodel.RentalViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CarScreen(
    customer: Customer,
    viewModel: CarViewModel,
    rentalViewModel: RentalViewModel
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Available Cars", "My Rentals", "Add New Car")
    var selectedCar by remember { mutableStateOf<Car?>(null) }
    var filterBrand by remember { mutableStateOf<String?>(null) }
    var rentalDays by remember { mutableIntStateOf(7) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Welcome, ${customer.name}!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            when (selectedTab) {
                0 -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Filter by Brand: ", modifier = Modifier.padding(end = 8.dp))
                        DropdownMenuBox(
                            brands = listOf(null) + viewModel.cars.value.map { it.brand }.distinct(),
                            selectedBrand = filterBrand,
                            onBrandSelected = { filterBrand = it }
                        )
                    }

                    LazyColumn {
                        items(viewModel.cars.value.filter { filterBrand == null || it.brand == filterBrand }) { car ->
                            CarCard(car, onClick = { selectedCar = car })
                        }
                    }

                    selectedCar?.let { car ->
                        AlertDialog(
                            onDismissRequest = { selectedCar = null },
                            title = { Text("Rent ${car.brand} ${car.model}") },
                            text = {
                                Column {
                                    Text("Year: ${car.year}")
                                    Spacer(Modifier.height(8.dp))
                                    Text("Select number of days:")
                                    Slider(
                                        value = rentalDays.toFloat(),
                                        onValueChange = { rentalDays = it.toInt() },
                                        valueRange = 1f..30f,
                                        steps = 29
                                    )
                                    Text("$rentalDays day(s)")
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    val rental = Rental(
                                        carId = car.id,
                                        customerId = customer.id,
                                        rentalDate = LocalDate.now().toString(),
                                        returnDate = LocalDate.now().plusDays(rentalDays.toLong()).toString()
                                    )
                                    rentalViewModel.addRental(rental)
                                    selectedCar = null
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Rental added!")
                                    }
                                }) {
                                    Text("Confirm Rental")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { selectedCar = null }) {
                                    Text("Cancel")
                                }
                            }
                        )
                    }
                }

                1 -> {
                    Text("Your Rentals", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(8.dp))

                    val rentals = rentalViewModel.rentals.value.filter { it.customerId == customer.id }

                    if (rentals.isEmpty()) {
                        Text("You have no rentals.")
                    } else {
                        LazyColumn {
                            items(rentals) { rental ->
                                val car = viewModel.cars.value.find { it.id == rental.carId }
                                if (car != null) {
                                    Card(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
                                        Column(Modifier.padding(12.dp)) {
                                            Text("${car.brand} ${car.model} (${car.year})")
                                            Text("From: ${rental.rentalDate}")
                                            Text("To: ${rental.returnDate}")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                2 -> {
                    Button(onClick = {
                        viewModel.addCar(
                            Car(
                                model = "Model " + ('A' + (0..25).random()).toChar(),
                                brand = listOf("Tesla", "BMW", "Audi", "Mercedes").random(),
                                year = (2015..2023).random()
                            )
                        )
                        scope.launch {
                            snackbarHostState.showSnackbar("Random car added.")
                        }
                    }) {
                        Text("Add Random Car")
                    }
                }
            }
        }

        SnackbarHost(snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}
