package com.example.myapplication.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.viewmodel.CustomerViewModel

@Composable
fun CarRentalLoginScreen(
    navController: NavController,
    customerViewModel: CustomerViewModel
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var loginAttempted by remember { mutableStateOf(false) }

    val loggedCustomer by customerViewModel.loggedCustomer.collectAsState()

    LaunchedEffect(loggedCustomer) {
        if (loginAttempted && loggedCustomer != null) {
            navController.navigate("carScreen/${loggedCustomer!!.id}") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (email.isNotBlank() && phone.isNotBlank()) {
                    loginAttempted = true
                    customerViewModel.login(email, phone)
                } else {
                    Toast.makeText(context, "Please enter email and phone", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate("register") }) {
            Text("Don't have an account? Register")
        }

        if (loginAttempted && loggedCustomer == null) {
            LaunchedEffect(email, phone) {
                Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

