package com.ayush.campuseaze.presentation.screen.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ayush.campuseaze.presentation.screen.mainscreen.LaundryScreen
import com.ayush.campuseaze.presentation.screen.mainscreen.MessScreen
import com.ayush.campuseaze.presentation.screen.mainscreen.RoomieScreen
import com.ayush.campuseaze.presentation.screen.mainscreen.StaysScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController ,
        startDestination = BottomBarScreen.StaysScreen.route)
    {
        composable(route = BottomBarScreen.StaysScreen.route){
            StaysScreen()
        }
        composable(route = BottomBarScreen.LaundryScreen.route){
            LaundryScreen()
        }
        composable(route = BottomBarScreen.MessScreen.route){
            MessScreen()
        }
        composable(route = BottomBarScreen.RoomieScreen.route){
            RoomieScreen()

        }
    }
}