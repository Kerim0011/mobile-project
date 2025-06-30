package com.example.myapplication.dao
import com.example.myapplication.data_model.Car
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Update
    suspend fun updateCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)

    @Query("SELECT * FROM cars")
    fun getAllCars(): Flow<List<Car>>

    @Query("SELECT * FROM cars WHERE id = :carId")
    suspend fun getCarById(carId: Int): Car?
}