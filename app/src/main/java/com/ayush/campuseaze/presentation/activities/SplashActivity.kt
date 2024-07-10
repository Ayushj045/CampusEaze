package com.ayush.campuseaze.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.KeyEventDispatcher.Component
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ayush.campuseaze.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
            finish()
        }, 1500)

    }
}

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.campus),
            contentDescription = "null",
            modifier = Modifier.size(300.dp)
        )
    }
}