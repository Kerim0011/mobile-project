package com.example.myapplication.data_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rentals")
data class Rental(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val carId: Int,
    val customerId: Int,
    val rentalDate: String,
    val returnDate: String
)