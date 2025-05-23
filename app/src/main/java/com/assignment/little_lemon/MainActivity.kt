package com.assignment.little_lemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.assignment.little_lemon.composables.Navigation
import com.assignment.little_lemon.ui.theme.LittlelemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
        enableEdgeToEdge()
        val menuDao = MenuDatabase.getInstance(applicationContext).getMenuDao()
        lifecycleScope.launch {
            try {

                val responseText =
                    client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                        .bodyAsText()
                val menuNetwork = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }.decodeFromString<MenuNetwork>(responseText)
                menuDao.deleteAll()
                for (menu in menuNetwork.menuList) {
                    Log.d("ids", menu.toMenuItem().id.toString())
                    menuDao.insertMenuItem(menu.toMenuItem())
                }
            } catch (e: Exception) {
                Log.d("network error", e.message.toString())
            }
        }


        setContent {
            LittlelemonTheme {
                var menuItemList by remember {
                    mutableStateOf(listOf<MenuItem2>())
                }
                LaunchedEffect(menuItemList.size) {
                    menuDao.getItems().asFlow().collect{
                        menuItemList=it
                    }
                }
//                var temp by remember {
//                    mutableStateOf(listOf<MenuItem2>())
//                }
//                temp = menuItemList.value
                val navController = rememberNavController()
                Navigation(navController, menuItemList)
            }
        }
//        }
    }
}

fun Menu.toMenuItem(): MenuItem2 {
    return MenuItem2(id, title, description, price, image, category)
}