package com.example.citiesapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.citiesapp.viewmodel.CityModel
import com.example.citiesapp.errorMessage
import com.example.citiesapp.util.makeToast

@Composable
fun DisplayDetails(cityModel: CityModel, id: Int) {
    val city = cityModel.city.value
    val context = LocalContext.current
    val loading = cityModel.loading.value
    val error = cityModel.error.value
    LaunchedEffect(true) {

        cityModel.getCity(id)
    }

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()

        }
    }


        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = city?.name ?: "", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            AsyncImage(
                model = city?.imageurl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = city?.description ?: "", fontSize = 16.sp, fontWeight = FontWeight.W300)

    }

    if(error) {
        errorMessage.makeToast(context)
        cityModel.error.value = false
    }
}