package com.example.citiesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.citiesapp.navigation.Destination
import com.example.citiesapp.screen.DisplayCities
import com.example.citiesapp.screen.DisplayDetails
import com.example.citiesapp.ui.theme.CitiesAppTheme
import com.example.citiesapp.viewmodel.CityModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitiesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val cityModel = ViewModelProvider(this)[CityModel::class.java]
                    val navController = rememberNavController()
                    MainApp(navController,cityModel)
                }
            }
        }
    }
}

@Composable
fun MainApp(navController: NavHostController,cityModel: CityModel){
    NavHost (navController = navController, startDestination = Destination.CityList.route) {
        composable(Destination.CityList.route) {
            DisplayCities(cityModel,navController)
        }

        composable(Destination.CityDetail.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")?.toInt()
            DisplayDetails(cityModel,id!!)
        }
    }
}



