package com.example.firstappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    lateinit var navController:NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            SetUpNavGraph(navController = navController)
            }
    }
}
