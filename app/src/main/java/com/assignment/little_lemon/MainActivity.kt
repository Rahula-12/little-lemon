package com.assignment.little_lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.assignment.little_lemon.composables.Navigation
import com.assignment.little_lemon.ui.theme.LittlelemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.request
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true // prevents crash on unexpected fields
                })
            }
        }
        enableEdgeToEdge()
        lifecycleScope.launch {
            val menuNetwork:MenuNetwork=client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").body()
            val menuDao=MenuDatabase.getInstance(applicationContext).getMenuDao()
            for(menu in menuNetwork.menuList) {
                menuDao.insertMenuItem(menu.toMenuItem())
            }
            menuDao.getItems().observe(this@MainActivity) {menuItemsList->
                setContent {
                    LittlelemonTheme {
                        val navController= rememberNavController()
                        Navigation(navController,menuItemsList)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittlelemonTheme {
        Greeting("Android")
    }
}

fun Menu.toMenuItem():MenuItem {
    return MenuItem(id,title,description,price,image,category)
}