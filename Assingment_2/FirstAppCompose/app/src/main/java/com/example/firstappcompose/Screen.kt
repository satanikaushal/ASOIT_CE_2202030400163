package com.example.firstappcompose

sealed class Screen(val Route: String) {
    object Home : Screen(Route = "main_screen")
    object Register : Screen(Route = "register_screen")
    object ForgetPassword : Screen(Route = "FrPass")
    object Confirm : Screen(Route = "Confirm")
    object Login : Screen(Route = "Login")
    object Slider : Screen(Route = "Slider")
    object Main: Screen(Route = "Main")
    object AllCategories: Screen(Route = "AllCategories")
}
