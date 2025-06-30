package com.example.myapplication.repository

import com.example.myapplication.dao.RentalDao
import com.example.myapplication.data_model.Rental
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RentalRepository @Inject constructor(
    private val rentalDao: RentalDao
) {
    suspend fun insertRental(rental: Rental) = rentalDao.insertRental(rental)

    suspend fun updateRental(rental: Rental) = rentalDao.updateRental(rental)

    suspend fun deleteRental(rental: Rental) = rentalDao.deleteRental(rental)

    fun getAllRentals(): Flow<List<Rental>> = rentalDao.getAllRentals()

    suspend fun getRentalById(id: Int): Rental? = rentalDao.getRentalById(id)
}
