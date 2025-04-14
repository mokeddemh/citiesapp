package com.example.citiesapp.repository

import com.example.citiesapp.service.Endpoint

object RepositoryHolder {
    val cityRepository by lazy { CityRepository(Endpoint.createInstance()) }
}

