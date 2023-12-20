package com.example.openapiexample

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.openapiexample.ui.screens.Routes
import com.example.openapiexample.ui.screens.home.HomeScreen
import com.example.openapiexample.ui.screens.splash.SplashScreen

@Composable
fun InitNavigation(modifier: Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier.statusBarsPadding(),
        navController = navController,
        startDestination = Routes.SPLASH.type
    ) {
        composable(Routes.SPLASH.type) { SplashScreen(navController = navController) }
        composable(Routes.HOME.type) { HomeScreen(navController = navController) }
    }
}