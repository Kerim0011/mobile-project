package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.CarRentalLoginScreen
import com.example.myapplication.ui.screen.CarRentalRegistrationScreen
import com.example.myapplication.ui.screen.CarScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.CustomerViewModel
import com.example.myapplication.viewmodel.CarViewModel
import com.example.myapplication.viewmodel.RentalViewModel
import com.example.myapplication.data_model.Customer
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        val customerViewModel = hiltViewModel<CustomerViewModel>()
                        CarRentalLoginScreen(navController, customerViewModel)
                    }

                    composable("register") {
                        val customerViewModel = hiltViewModel<CustomerViewModel>()
                        CarRentalRegistrationScreen(navController, customerViewModel)
                    }

                    composable(
                        "carScreen/{customerId}",
                        arguments = listOf(navArgument("customerId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val customerId = backStackEntry.arguments?.getInt("customerId") ?: 0
                        val customerViewModel = hiltViewModel<CustomerViewModel>()
                        val carViewModel = hiltViewModel<CarViewModel>()
                        val rentalViewModel = hiltViewModel<RentalViewModel>()

                        var customer by remember { mutableStateOf<Customer?>(null) }

                        LaunchedEffect(customerId) {
                            customer = customerViewModel.getCustomerById(customerId)
                        }

                        customer?.let {
                            CarScreen(
                                customer = it,
                                viewModel = carViewModel,
                                rentalViewModel = rentalViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
