package com.ayush.campuseaze.utils

sealed class Route(val route: String) {
    data object LoginScreen: Route("login")
    data object SignupScreen: Route("signup")
}