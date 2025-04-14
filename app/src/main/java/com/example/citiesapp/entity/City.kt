package com.example.citiesapp.entity

import com.google.gson.annotations.SerializedName

data class City (
    val id: Int,
    val name: String,
    @SerializedName("imageurl")
    val imageUrl:String,
    val description:String? = null
)