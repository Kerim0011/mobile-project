package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data_model.Car
import com.example.myapplication.repository.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel




@HiltViewModel
class CarViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars.asStateFlow()

    init {
        loadCars()
    }

    private fun loadCars() {
        viewModelScope.launch {
            carRepository.getAllCars().collect { carList ->
                _cars.value = carList
            }
        }
    }

    fun addCar(car: Car) {
        viewModelScope.launch {
            carRepository.insertCar(car)
        }
    }

    fun updateCar(car: Car) {
        viewModelScope.launch {
            carRepository.updateCar(car)
        }
    }

    fun removeCar(car: Car) {
        viewModelScope.launch {
            carRepository.deleteCar(car)
        }
    }

    // Keep addToRentals only if you want rentals functionality
    fun addToRentals(customerId: Int, carId: Int, rentalDate: String, returnDate: String) {
        viewModelScope.launch {
            val car = cars.value.find { it.id == carId }
            if (car != null) {
                carRepository.insertCarToRentals(car, customerId, rentalDate, returnDate)
            }
        }
    }
}
