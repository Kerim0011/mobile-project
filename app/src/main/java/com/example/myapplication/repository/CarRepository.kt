package com.example.myapplication.repository

import com.example.myapplication.dao.CarDao
import com.example.myapplication.dao.RentalDao
import com.example.myapplication.data_model.Car
import com.example.myapplication.data_model.Rental
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepository @Inject constructor(
    private val carDao: CarDao,
    private val rentalDao: RentalDao
) {

    suspend fun insertCar(car: Car) = carDao.insertCar(car)

    suspend fun updateCar(car: Car) = carDao.updateCar(car)

    suspend fun deleteCar(car: Car) = carDao.deleteCar(car)

    fun getAllCars(): Flow<List<Car>> = carDao.getAllCars()

    suspend fun getCarById(id: Int): Car? = carDao.getCarById(id)

    suspend fun insertCarToRentals(car: Car, customerId: Int, rentalDate: String, returnDate: String) {

        val rental = Rental(
            id = 0,
            carId = car.id,
            customerId = customerId,
            rentalDate = rentalDate,
            returnDate = returnDate
        )
        rentalDao.insertRental(rental)
    }
}
