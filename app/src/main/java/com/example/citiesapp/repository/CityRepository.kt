package com.example.citiesapp.repository

import com.example.citiesapp.service.Endpoint

object CityRepository {

    private val endpoint = Endpoint.createEndpoint()

    suspend fun getCities() = endpoint.getCities();

    suspend fun getCity(id:Int) = endpoint.getCity(id);

}