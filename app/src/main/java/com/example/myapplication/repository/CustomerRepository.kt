package com.example.myapplication.repository

import com.example.myapplication.dao.CustomerDao
import com.example.myapplication.data_model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao
) {
    suspend fun insertCustomer(customer: Customer) = customerDao.insertCustomer(customer)

    suspend fun updateCustomer(customer: Customer) = customerDao.updateCustomer(customer)

    suspend fun deleteCustomer(customer: Customer) = customerDao.deleteCustomer(customer)

    fun getAllCustomers(): Flow<List<Customer>> = customerDao.getAllCustomers()

    suspend fun getCustomerById(id: Int): Customer? = customerDao.getCustomerById(id)


    suspend fun login(email: String, phone: String): Customer? {
        return customerDao.getCustomerByEmailAndPhone(email, phone)
    }


    suspend fun register(customer: Customer) {
        insertCustomer(customer)
    }
}
