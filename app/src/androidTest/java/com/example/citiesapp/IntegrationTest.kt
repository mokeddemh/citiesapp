package com.example.citiesapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.citiesapp.entity.City
import com.example.citiesapp.repository.CityRepository
import com.example.citiesapp.service.Endpoint
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class IntegrationTest {
        private lateinit var endpoint: Endpoint
        private lateinit var repository: CityRepository

        @Before
        fun setup() {
            endpoint = Endpoint.createInstance()
            repository = CityRepository(endpoint)
        }



    @Test
    fun testIntegrationGetCities() {

        val expectedCities  = listOf(
            City(1, "Barcelone", "http://197.140.142.82:8082/images/main/barcelona.png"),
            City(2, "Istanbul", "http://197.140.142.82:8082/images/main/istanbul.png"),
            City(3, "Londres", "http://197.140.142.82:8082/images/main/london.png"),
            City(4, "Paris", "http://197.140.142.82:8082/images/main/paris.png"),
            City(5, "Rome", "http://197.140.142.82:8082/images/main/roma.png")
        )

        runBlocking {
            val list  = repository.getCities()
            assertArrayEquals(expectedCities.toTypedArray(),list?.toTypedArray())
        }

    }


    @Test
    fun testIntegrationGetCity() {

        runBlocking {
            val city  = repository.getCity(1)
            assertEquals("Barcelone",city.name)
            assertNotNull(city.description)
        }

    }
}