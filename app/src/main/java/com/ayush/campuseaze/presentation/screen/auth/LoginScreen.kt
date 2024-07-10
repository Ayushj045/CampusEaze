package com.ayush.campuseaze.presentation.screen.auth

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.campuseaze.R
import com.ayush.campuseaze.presentation.activities.HomeActivity
import com.ayush.campuseaze.presentation.components.Loading
import com.ayush.campuseaze.presentation.components.Toast
import com.ayush.campuseaze.presentation.viewmodel.AuthViewModel
import com.ayush.campuseaze.utils.Route
import com.ayush.campuseaze.utils.State



@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {

    val ctx = LocalContext.current as Activity
//    LaunchedEffect(key1 = Unit) {
//        if(viewModel.isLoggedIn()) {
//            ctx.startActivity(Intent(ctx, HomeActivity::class.java))
//        }
//    }

    viewModel.signIn.collectAsState().value.let {
        when(it) {
            is State.Error -> {
                Toast(ctx, it.message)
            }
            State.Loading -> Loading()
            State.None -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.TopStart
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        // Logo
                        Image(
                            painter = painterResource(id = R.drawable.campus),
                            contentDescription = "CampusEase Logo",
                            modifier = Modifier
                                .height(100.dp)
                                .width(200.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Email TextField
                        var email by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .padding(8.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))


                        var password by remember { mutableStateOf("") }
                        var passwordVisible by remember { mutableStateOf(false) }
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, contentDescription = null)
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .padding(8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))


                        Button(
                            onClick = {
                                if(email.isNotEmpty()) {
                                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                        if(password.isNotEmpty()) {
                                            viewModel.signIn(email = email.trim(), password = password)
                                        } else {
                                            Toast(ctx, "Please enter your password!")
                                        }
                                    } else {
                                        Toast(ctx, "Please enter a valid email address!")
                                    }
                                } else {
                                    Toast(ctx, "Please enter your email!")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = "Login")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Sign Up Text
                        TextButton(onClick = {
                            navController.navigate(Route.SignupScreen.route)
                        }) {
                            Text("Don't have an account? Sign Up")
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.curve),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    )
                }
            }
            is State.Success -> {
                ctx.startActivity(Intent(ctx, HomeActivity::class.java))
            }
        }
    }
}

