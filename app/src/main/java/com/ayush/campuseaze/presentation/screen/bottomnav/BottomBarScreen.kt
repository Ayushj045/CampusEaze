package com.ayush.campuseaze.presentation.screen.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalLaundryService
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route:String,
    val title:String,
    val icon:ImageVector

) {
    object StaysScreen: BottomBarScreen(
        route = "stays",
        title = "Stays",
        icon = Icons.Default.Home
    )

    object LaundryScreen: BottomBarScreen(
        route = "laundry",
        title = "Laundry",
        icon = Icons.Default.LocalLaundryService
    )

    object MessScreen: BottomBarScreen(
        route = "mess",
        title = "Mess",
        icon = Icons.Default.EmojiFoodBeverage
    )
    object RoomieScreen: BottomBarScreen(
        route = "roomie",
        title = "Roomie",
        icon = Icons.Default.SupervisedUserCircle
    )

}