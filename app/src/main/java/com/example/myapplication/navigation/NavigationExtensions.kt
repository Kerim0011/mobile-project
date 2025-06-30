package com.example.myapplication.navigation

import androidx.navigation.NavController
import com.example.myapplication.data_model.Customer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun NavController.navigateToCarScreen(customer: Customer) {
    val customerJson = Json.encodeToString(customer)
    navigate("carScreen/$customerJson")
}