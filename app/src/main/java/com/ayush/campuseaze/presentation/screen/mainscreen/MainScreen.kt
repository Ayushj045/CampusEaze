package com.ayush.campuseaze.presentation.screen.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ayush.campuseaze.presentation.screen.bottomnav.BottomBarScreen
import com.ayush.campuseaze.presentation.screen.bottomnav.BottomNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        BottomBarScreen.StaysScreen,
        BottomBarScreen.LaundryScreen,
        BottomBarScreen.MessScreen,
        BottomBarScreen.RoomieScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {
        screens.forEach { screens ->
            AddItem(
                screen = screens,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    BottomNavigationItem(label = {
        Text(
            text = screen.title, modifier = Modifier.padding(bottom = 3.dp),
            fontSize = 12.sp
        )
    },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = { navController.navigate(screen.route) }, icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        alwaysShowLabel = false,
        selectedContentColor = Color(0xFF175795)

    )

}