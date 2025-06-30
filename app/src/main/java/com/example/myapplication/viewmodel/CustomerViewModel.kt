package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data_model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.myapplication.repository.CustomerRepository


@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    private val _loggedCustomer = MutableStateFlow<Customer?>(null)
    val loggedCustomer: StateFlow<Customer?> = _loggedCustomer.asStateFlow()

    private val _customers = MutableStateFlow<List<Customer>>(emptyList())
    val customers: StateFlow<List<Customer>> = _customers.asStateFlow()

    private val _registrationError = MutableStateFlow<String?>(null)
    val registrationError: StateFlow<String?> = _registrationError.asStateFlow()

    init {
        // Load all customers from DB when ViewModel is created
        viewModelScope.launch {
            repository.getAllCustomers().collect { customerList ->
                _customers.value = customerList
            }
        }
    }

    fun login(email: String, phone: String) {
        viewModelScope.launch {
            val customer = repository.login(email.trim(), phone.trim())
            _loggedCustomer.value = customer
        }
    }

    fun register(newCustomer: Customer) {
        viewModelScope.launch {
            val existing = repository.login(newCustomer.email, newCustomer.phone)
            if (existing != null) {
                _registrationError.value = "Email already registered"
                return@launch
            }

            repository.register(newCustomer)
            // Fetch the inserted customer by email+phone to get its generated ID
            val inserted = repository.login(newCustomer.email, newCustomer.phone)
            _loggedCustomer.value = inserted
            _registrationError.value = null
        }
    }

    fun logout() {
        _loggedCustomer.value = null
    }

    suspend fun getCustomerById(id: Int): Customer? {
        return repository.getCustomerById(id)
    }
}