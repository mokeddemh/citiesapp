package com.example.citiesapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.citiesapp.errorMessage
import com.example.citiesapp.navigation.Destination
import com.example.citiesapp.util.makeToast
import com.example.citiesapp.viewmodel.CityModel


@Composable
fun DisplayCities(cityModel: CityModel, navController: NavHostController) {
    val context = LocalContext.current
    val data = cityModel.cities.value
    val loading = cityModel.loading.value
    val error = cityModel.error.value



        if (loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()

            }
        }
        LazyColumn {
            items(data) {
                Card(
                    modifier = Modifier.clickable {
                   navController.navigate(Destination.CityDetail.createRoute(it.id))
                    }.padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF7F2F9)
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = it.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        AsyncImage(
                            model = it.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

    if(error) {
        errorMessage.makeToast(context)
        cityModel.error.value = false
    }

    }



