package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dao.CarDao
import com.example.myapplication.dao.CustomerDao
import com.example.myapplication.dao.RentalDao
import com.example.myapplication.data_model.Car
import com.example.myapplication.data_model.Customer
import com.example.myapplication.data_model.Rental

@Database(
    entities = [Car::class, Customer::class, Rental::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
    abstract fun customerDao(): CustomerDao
    abstract fun rentalDao(): RentalDao

}
