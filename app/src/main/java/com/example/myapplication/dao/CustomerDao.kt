package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.data_model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("SELECT * FROM customers")
    fun getAllCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    suspend fun getCustomerById(customerId: Int): Customer?

    @Query("SELECT * FROM customers WHERE email = :email AND phone = :phone LIMIT 1")
    suspend fun getCustomerByEmailAndPhone(email: String, phone: String): Customer?
}