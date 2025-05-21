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
    @SerialName("id")
    val id:Int,
    @SerialName("title")
    val title:String,
    @SerialName("description")
    val description:String,
    @SerialName("price")
    val price:String,
    @SerialName("image")
    val image:String,
    @SerialName("category")
    val category:String
)