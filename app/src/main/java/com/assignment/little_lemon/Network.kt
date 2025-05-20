package com.assignment.little_lemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menuList:List<Menu>
)

@Serializable
data class Menu(
    val id:Int,
    val title:String,
    val description:String,
    val price:String,
    val image:String,
    val category:String
)