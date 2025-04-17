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

    init {
        getCities()
    }

    fun getCities() {
        loading.value = true
        viewModelScope.launch {
            try {
                cities.value = repository.getCities()
            }
            catch (e:Exception) {
                error.value = true
            }
            finally {
                loading.value = false
            }

        }
    }

    fun getCity(id:Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                city.value = repository.getCity(id)

            }
            catch (e:Exception) {
                error.value = true
            }
            finally {
                loading.value = false
            }

        }
    }




}