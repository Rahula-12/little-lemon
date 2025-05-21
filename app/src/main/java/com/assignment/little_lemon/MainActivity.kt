package com.assignment.little_lemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
        lifecycleScope.launch {
            val menuDao=MenuDatabase.getInstance(applicationContext).getMenuDao()
            try{
                menuDao.deleteAll()
                val responseText=client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").bodyAsText()
                val menuNetwork=Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }.decodeFromString<MenuNetwork>(responseText)
                for(menu in menuNetwork.menuList) {
                    Log.d("ids",menu.toMenuItem().id.toString())
                    menuDao.insertMenuItem(menu.toMenuItem())
                }
            }
            catch(e:Exception) {
                Log.d("network error",e.message.toString())
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

fun Menu.toMenuItem():MenuItem2 {
    return MenuItem2(id,title,description,price,image,category)
}