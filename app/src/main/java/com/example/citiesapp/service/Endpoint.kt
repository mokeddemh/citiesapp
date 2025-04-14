package com.example.citiesapp.service

import com.example.citiesapp.baseUrl
import com.example.citiesapp.entity.City
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    @GET("getlistcities")
    suspend fun getCities(): Response<List<City>>

    @GET("getcity/{id}")
    suspend fun getCity(@Path("id") id: Int): Response<City>


    companion object {
        private var INSTANCE: Endpoint? = null
        fun createInstance(): Endpoint {
            if(INSTANCE ==null) {
                INSTANCE = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().
                    create(Endpoint::class.java)
            }
            return INSTANCE!!
        }
    }

}