package com.alamilla.pokedex.feature.pokemongps

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alamilla.pokedex.R
import com.alamilla.pokedex.feature.pokemongps.viewmodel.LocationViewModel
import com.alamilla.pokedex.navigation.AppScreens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
fun PokemonGpsScreen(navController: NavHostController) {

    val locationViewModel: LocationViewModel = hiltViewModel()

    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0F6080),
                        Color(0xFF0F6080),
                        Color(0xFF0F6080)
                    )
                )
            )
            .fillMaxSize()
    ) {
        NotificationScreen(locationViewModel, navController)
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationScreen(locationViewModel: LocationViewModel, navController: NavHostController) {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    LaunchedEffect(true) {
        permissionState.launchMultiplePermissionRequest()
    }

    val locationState = remember { mutableStateOf<Location?>(null) }
    var distance by rememberSaveable { mutableStateOf(0.0) }
    var lastLocation: Location? = null

    val context = LocalContext.current

    // Solicitar permisos y actualizar la ubicación cuando se conceden
    if (permissionState.allPermissionsGranted) {
        val locationManager =
            LocalContext.current.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val provider = LocationManager.GPS_PROVIDER
        val minTime = 3000L // 10 segundos
        val minDistance = 0f // 0 metros

        LaunchedEffect(true) {
            locationManager.requestLocationUpdates(provider, minTime, minDistance) { location ->
                if (lastLocation != null) {
                    distance += lastLocation!!.distanceTo(location)

                    if (distance >= 10.0) {
                        locationViewModel.showPokemon()
                        Toast.makeText(
                            context,
                            "Superaste 10 metros, pokemon encontrado!",
                            Toast.LENGTH_SHORT
                        ).show()
                        distance = 0.0
                    }
                }

                Log.i("location", "$distance m")

                locationState.value = location
                lastLocation = location
            }
        }
    }

    // Contenido de la interfaz de usuario
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        // Mostrar la distancia
        Text(
            fontFamily = FontFamily(Font(R.font.righteous_regular)),
            text = "Metros recorridos: ",
            fontSize = 24.sp
        )

        Text(
            fontFamily = FontFamily(Font(R.font.righteous_regular)),
            text = "%.2f m".format(distance),
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botón para pedir permisos
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDB4136),
            ),
            onClick = { permissionState.launchMultiplePermissionRequest() }) {
            Text(
                color = Color.White,
                text = "Permiso",
                fontFamily = FontFamily(Font(R.font.righteous_regular))
            )
            Icon(
                modifier = Modifier.size(20.dp),
                tint = Color(0xFFDDDDDD),
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.height(170.dp))

        val pokemon by locationViewModel.pokemonModel.observeAsState(initial = null)

        if (pokemon != null) {

                Text(modifier = Modifier.padding(8.dp), text = pokemon!!.name, fontFamily = FontFamily(Font(R.font.righteous_regular)),
                    color = Color(0xFFE4E4E4)
                )
                Button(
                    onClick = { navController.navigate("${AppScreens.DetailScreen.route}/${pokemon!!.id}") },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF19D181)
                    )
                ) {
                    Text(text = "Ver detalles")
                }

            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if (locationViewModel.vibrate.value == true) {
                locationViewModel.changeVibrateValue()
                locationViewModel.vibrate(vibrator)
            }

            Spacer(modifier = Modifier.height(20.dp))

        }

        Image(
            modifier = Modifier
                .size(70.dp)
                .padding(bottom = 8.dp),
            painter = painterResource(id = R.drawable.pokemon_location),
            contentDescription = ""
        )
        Text(
            text = "Recuerda activar tu GPS",
            fontFamily = FontFamily(Font(R.font.righteous_regular)),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))

        if (permissionState.shouldShowRationale) {
            Text(
                text = "Tiene que activar los permisos para poder mostrar la distancia",
                fontFamily = FontFamily(Font(R.font.righteous_regular)),
                color = Color.White
            )
        } else if (!permissionState.allPermissionsGranted) {
            Text(
                text = "Acepta los permisos para calular la distancia",
                fontFamily = FontFamily(Font(R.font.righteous_regular)),
                color = Color(
                    0x74FFFFFF
                )
            )
        }
    }
}