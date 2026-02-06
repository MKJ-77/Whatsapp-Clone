package com.mkj.whatsapp.presentation.navigation

sealed class Routes(val route: String) {

    object Splash : Routes("SplashScreen")

    object Welcome : Routes("WelcomeScreen")

    object UserRegistration : Routes("UserRegistrationScreen")

    object Home : Routes("HomeScreen")

    object Update : Routes("UpdateScreen")

    object Community : Routes("CommunityScreen")

    object Calling : Routes("CallingScreen")
}
