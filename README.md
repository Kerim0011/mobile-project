#  Mobile Car Rental App

An Android-based Car Rental application built with **Kotlin**, using **Jetpack Compose**, **Room Database**, and **MVVM architecture**. The app supports core functionalities such as user registration, login, viewing and adding cars, and inserting random car data for quick testing. It also includes a foreground service for enhanced background functionality.

---

##  Features

-  **User Authentication**
  - Register and log in securely
  - Simple and responsive UI with Jetpack Compose

-  **Car Management**
  - Add new cars with details
  - View a list of available cars
  - Add a **random test car** for demo or testing

- 🗃 **Local Storage**
  - Room Database for offline persistence
  - Data access handled through DAO and Repository layers

-  **Architecture**
  - MVVM (Model-View-ViewModel)
  - Clean separation of concerns
  - Dependency Injection with Hilt (if applicable)

-  **Foreground Service**
  - Long-running tasks supported via foreground service
  - Integrated with `ViewModel` for lifecycle-aware handling

---

##  Project Structure

Mobile-Project/
├── dao/ # Data Access Objects for Room
├── data_model/ # Entity and data classes (e.g., Car, Customer, Rental)
├── database/ # Room database instance and config
├── di/ # Dependency Injection modules
├── model/ # Business logic models
├── navigation/ # Navigation components and routes
├── repository/ # Data repositories
├── services/ # Foreground services and related logic
├── ui/ # Jetpack Compose UI screens
├── utils/ # Utility functions/helpers
├── viewmodel/ # ViewModels for screen-specific logic
├── CarRentalApp.kt # App entry point
├── MainActivity.kt # Main activity


---

##  How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd Mobile-Project
Open in Android Studio

Run on emulator or physical device

The app supports Android API 21+

Uses Jetpack Compose for UI — no XML required



 Dependencies
Kotlin

Jetpack Compose

Room

LiveData / ViewModel

Coroutine support

 Hilt for DI
 

TODO / Future Improvements

Add user roles (Admin/User)

Enable editing and deleting cars

Add rental history tracking

Firebase authentication and sync (optional)

UI improvements and animations

Author
Kerim Senderovic

Contact
For issues, suggestions, or collaboration, feel free to reach out on GitHub.
