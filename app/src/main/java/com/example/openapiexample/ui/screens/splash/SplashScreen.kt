package com.example.openapiexample.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.openapiexample.ui.screens.Routes
import com.example.openapiexample.ui.screens.component.SplashAnimatedLogo
import kotlinx.coroutines.delay

private const val SPLASH_DELAY_MILLIS = 3000L

@Composable
fun SplashScreen(navController: NavHostController = rememberNavController()) {

    LaunchedEffect(key1 = Unit) {
        delay(SPLASH_DELAY_MILLIS)
        navController.navigate(Routes.HOME.type) {
            popUpTo(Routes.SPLASH.type) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            SplashAnimatedLogo(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}