package com.assignment.little_lemon.composables

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Destination(val route:String) {
    data object Onboarding:Destination("Onboarding")
    data object Home:Destination("Home")
    data object Profile:Destination("Profile")
}
@Composable
fun Navigation(navController:NavHostController) {
    val userData=LocalContext.current.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    val firstName=userData.getString("FirstName","")?:""
    val lastName=userData.getString("LastName","")?:""
    val email=userData.getString("email","")?:""
    NavHost(startDestination=if(firstName=="") Destination.Onboarding.route else Destination.Home.route, navController = navController) {
        composable(Destination.Onboarding.route){
            Onboarding(navController = navController, saveInSharedPref = {first,last,mail->
                userData.edit().putString("FirstName",first).putString("LastName",last).putString("email",mail).commit()
            })
        }
        composable(Destination.Home.route){

        }
        composable(Destination.Profile.route){
            Profile(Modifier,firstName,lastName,email){
                navController.navigate(Destination.Onboarding.route)
            }
        }
    }
}