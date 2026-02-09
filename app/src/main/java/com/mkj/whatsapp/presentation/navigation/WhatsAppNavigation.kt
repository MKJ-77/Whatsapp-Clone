package com.mkj.whatsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mkj.whatsapp.presentation.auth.UserRegistrationScreen
import com.mkj.whatsapp.presentation.calling_screen.CallingScreen
import com.mkj.whatsapp.presentation.chat_detail.ChatDetailScreen
import com.mkj.whatsapp.presentation.community_screen.CommunityScreen
import com.mkj.whatsapp.presentation.home_screen.HomeScreen
import com.mkj.whatsapp.presentation.splash.SplashScreen
import com.mkj.whatsapp.presentation.update_screen.UpdateScreen
import com.mkj.whatsapp.presentation.welcome.WelcomeScreen

@Composable
fun WhatsAppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {

        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }

        composable(Routes.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(Routes.UserRegistration.route) {
            UserRegistrationScreen(navController)
        }

        composable(Routes.Home.route) {
            HomeScreen(navController)
        }

        composable(Routes.Update.route) {
            UpdateScreen(navController)
        }

        composable(Routes.Community.route) {
            CommunityScreen(navController)
        }

        composable(Routes.Calling.route) {
            CallingScreen(navController)
        }
        composable(
            route = Routes.ChatDetail.route
        ) { backStackEntry ->
            val userName =
                backStackEntry.arguments?.getString("userName") ?: ""

            ChatDetailScreen(
                navController = navController,
                userName = userName
            )
        }
    }
}
