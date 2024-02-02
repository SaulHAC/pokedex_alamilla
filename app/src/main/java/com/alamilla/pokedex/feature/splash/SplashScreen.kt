package com.alamilla.pokedex.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.alamilla.pokedex.R
import com.alamilla.pokedex.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navControllerSplash: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(1500)
        navControllerSplash.popBackStack()
        navControllerSplash.navigate(AppScreens.Home.route)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Splash()
    }
}

@Preview
@Composable
fun Splash() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (textLoading) = createRefs()

        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.splash),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.constrainAs(textLoading) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }.padding(bottom = 50.dp),
            text = "LOADING...",
            color = Color.White,
            fontSize = 28.sp,
            style = TextStyle(fontFamily = FontFamily(Font(R.font.righteous_regular)))
        )
    }
}
