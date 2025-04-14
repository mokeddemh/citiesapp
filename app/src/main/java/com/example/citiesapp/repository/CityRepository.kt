package com.example.citiesapp.repository

import com.example.citiesapp.service.Endpoint

class CityRepository(private val endpoint: Endpoint) {

    suspend fun getCities() = endpoint.getCities();

    suspend fun getCity(id:Int) = endpoint.getCity(id);

}