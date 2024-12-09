package com.example.citiesapp.navigation

sealed class Destination(val route:String) {
    object CityList : Destination("cityList")
    object CityDetail : Destination("cityDetail/{id}") {
        fun createRoute(id: Int) = "cityDetail/$id"
    }
}