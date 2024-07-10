package com.ayush.campuseaze.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ayush.campuseaze.navigation.AuthNavigation
import com.ayush.campuseaze.presentation.ui.theme.CampusEazeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CampusEazeTheme {
                AuthNavigation()
            }
        }
    }
}