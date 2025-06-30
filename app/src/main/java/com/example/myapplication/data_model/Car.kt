package com.example.myapplication.data_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val model: String,
    val brand: String,
    val year: Int
)