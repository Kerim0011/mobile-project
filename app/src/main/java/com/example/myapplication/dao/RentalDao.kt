package com.example.myapplication.dao
import com.example.myapplication.data_model.Rental
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RentalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRental(rental: Rental)

    @Update
    suspend fun updateRental(rental: Rental)

    @Delete
    suspend fun deleteRental(rental: Rental)

    @Query("SELECT * FROM rentals")
    fun getAllRentals(): Flow<List<Rental>>

    @Query("SELECT * FROM rentals WHERE id = :rentalId")
    suspend fun getRentalById(rentalId: Int): Rental?
}