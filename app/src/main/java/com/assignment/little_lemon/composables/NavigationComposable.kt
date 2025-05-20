package com.assignment.little_lemon.composables

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

sealed class Destination(val route:String) {
    data object Onboarding:Destination("Onboarding")
    data object Home:Destination("Home")
    data object Profile:Destination("Profile")
}
@Composable
fun Navigation(navController:NavHostController) {
    val userData=LocalContext.current.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    val firstName=userData.getString("FirstName","")
    val lastName=userData.getString("LastName","")
    val email=userData.getString("email","")
    NavHost(startDestination=if(firstName=="") Destination.Onboarding.route else Destination.Home.route, navController = navController) {

    }
}