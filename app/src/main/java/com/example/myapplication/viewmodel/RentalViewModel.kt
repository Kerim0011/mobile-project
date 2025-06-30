package com.example.myapplication.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data_model.Rental
import com.example.myapplication.repository.RentalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentalViewModel @Inject constructor(
    private val repository: RentalRepository
) : ViewModel() {

    private val _rentals = mutableStateOf<List<Rental>>(emptyList())
    val rentals: State<List<Rental>> = _rentals

    init {
        observeRentals()
    }

    private fun observeRentals() {
        viewModelScope.launch {
            repository.getAllRentals().collectLatest {
                _rentals.value = it
            }
        }
    }

    fun addRental(rental: Rental) = viewModelScope.launch {
        repository.insertRental(rental)
        // No need to call observeRentals() again — Flow will emit automatically
    }
}
