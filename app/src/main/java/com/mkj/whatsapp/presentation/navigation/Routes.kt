package com.mkj.whatsapp.presentation.navigation
    import java.net.URLEncoder
    import java.nio.charset.StandardCharsets

sealed class Routes(val route: String) {

    object Splash : Routes("SplashScreen")

    object Welcome : Routes("WelcomeScreen")

    object UserRegistration : Routes("UserRegistrationScreen")

    object Otp : Routes("otp/{phone}") {
        fun createRoute(phone: String): String =
            "otp/$phone"
    }

    object Home : Routes("HomeScreen")

    object Update : Routes("UpdateScreen")

    object Community : Routes("CommunityScreen")

    object Calling : Routes("CallingScreen")

    object ChatDetail : Routes("ChatDetailScreen/{userName}") {
        fun createRoute(userName: String): String =
            "ChatDetailScreen/${URLEncoder.encode(userName, StandardCharsets.UTF_8.toString())}"
    }

}
