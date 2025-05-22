package com.assignment.little_lemon.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignment.little_lemon.MenuItem2

sealed class Destination(val route:String) {
    data object Onboarding:Destination("Onboarding")
    data object Home:Destination("Home")
    data object Profile:Destination("Profile")
}
@Composable
fun Navigation(navController:NavHostController,menuItems:List<MenuItem2>) {
    val userData=LocalContext.current.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    var firstName=userData.getString("FirstName","")?:""
    var lastName: String
    var email: String
    NavHost(startDestination=if(firstName=="") Destination.Onboarding.route else Destination.Home.route, navController = navController) {
        composable(Destination.Onboarding.route){
            Onboarding(navController = navController, saveInSharedPref = {first,last,mail->
                userData.edit().putString("FirstName",first).putString("LastName",last).putString("email",mail).commit()
            })
        }
        composable(Destination.Home.route){
            Home(Modifier,menuItems,navController)
        }
        composable(Destination.Profile.route){
            firstName=userData.getString("FirstName","")?:""
             lastName=userData.getString("LastName","")?:""
             email=userData.getString("email","")?:""
            Profile(Modifier,firstName,lastName,email){
                userData.edit().putString("FirstName","").putString("LastName","").putString("email","").commit()
                navController.navigate(Destination.Onboarding.route)
            }
        }
    }
}