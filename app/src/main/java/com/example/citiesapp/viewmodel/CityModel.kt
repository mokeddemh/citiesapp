package com.example.citiesapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citiesapp.entity.City
import com.example.citiesapp.repository.CityRepository
import kotlinx.coroutines.launch

class CityModel(private val repository: CityRepository):ViewModel() {
    val cities =  mutableStateOf(emptyList<City>())
    val loading = mutableStateOf(false)
    val error = mutableStateOf(false)
    val city = mutableStateOf<City?>(null)

     fun getCities() {
         loading.value = true
        viewModelScope.launch {
            val response = repository.getCities()
            loading.value = false
            if(response.isSuccessful) {
                cities.value = response.body()!!
            }
            else {
                error.value = true
            }

        }
    }



    fun getCity(id:Int) {
        loading.value = true
        viewModelScope.launch {
            val response = repository.getCity(id)
            loading.value = false
            if(response.isSuccessful) {
                city.value = response.body()
            }
            else {
                error.value = true
            }

        }
    }




}