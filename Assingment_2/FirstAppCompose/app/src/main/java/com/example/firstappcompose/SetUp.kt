package com.example.firstappcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavGraph(navController: NavHostController){
    NavHost(navController = navController ,
    startDestination = Screen.Home.Route){

        composable(
            route = Screen.Home.Route
        ){
            HomeScreen(navController)
        }

        composable(
            route = Screen.Login.Route
        ){
            Slider(navController = navController)
        }

        composable(
            route = Screen.Slider.Route
        ){
            Slider(navController = navController)
        }

        composable(
            route = Screen.Register.Route
        ){
             Slider(pageCount = 1,navController = navController)
        }

        composable(
            route = Screen.ForgetPassword.Route
        ){
             ForgotPassword(navController)
        }

        composable(
            route = Screen.Confirm.Route
        ){
            Confirmation(navController)
        }
        composable(
            route = Screen.Main.Route
        ){
            Main(navController)
        }
        composable(
            route = Screen.AllCategories.Route
        ){
            AllCategories(navController)
        }
    }
}