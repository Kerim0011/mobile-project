package com.example.myapplication.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import com.example.myapplication.data_model.Customer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.utils.isValidEmail
import com.example.myapplication.viewmodel.CustomerViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CarRentalRegistrationScreen(
    navController: NavHostController,
    customerViewModel: CustomerViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }

    val context = LocalContext.current
    val loggedCustomer by customerViewModel.loggedCustomer.collectAsState()
    val registrationError by customerViewModel.registrationError.collectAsState()

    LaunchedEffect(loggedCustomer) {
        loggedCustomer?.let { customer ->
            navController.navigate("carScreen/${customer.id}") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    LaunchedEffect(registrationError) {
        registrationError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1B1B)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.car22),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .padding(30.dp)
                .height(400.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = if (isValidEmail(it)) "" else "Invalid email"
                },
                label = { Text("Email", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                isError = emailError.isNotEmpty(),
                supportingText = { if (emailError.isNotEmpty()) Text(emailError, color = Color.Red) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isBlank() || firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (emailError.isNotEmpty()) {
                        Toast.makeText(context, "Please fix email error", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    customerViewModel.register(
                        Customer(
                            name = "$firstName $lastName",
                            email = email,
                            phone = phoneNumber
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007F9C))
            ) {
                Text("Register", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Already have an account? Login", color = Color.White)
            }
        }
    }
}