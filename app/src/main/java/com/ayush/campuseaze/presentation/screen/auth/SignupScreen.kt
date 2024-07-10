package com.ayush.campuseaze.presentation.screen.auth

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayush.campuseaze.R
import com.ayush.campuseaze.data.model.User
import com.ayush.campuseaze.presentation.activities.HomeActivity
import com.ayush.campuseaze.presentation.components.Loading
import com.ayush.campuseaze.presentation.components.Toast
import com.ayush.campuseaze.presentation.viewmodel.AuthViewModel
import com.ayush.campuseaze.utils.State

@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember {
        mutableStateOf("")
    }
    var age by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember {
        mutableStateOf("")
    }
    var gender by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val genders = listOf("Male", "Female")
    val ctx = LocalContext.current

    viewModel.signUp.collectAsState().value.let {
        when(it) {
            is State.Error -> Toast(ctx, it.message)
            State.Loading -> Loading()
            State.None -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_signup),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .padding(bottom = 25.dp)
                                .height(100.dp)
                                .width(200.dp)
                        )
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        var passwordVisible by remember { mutableStateOf(false) }
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { androidx.compose.material3.Text("Password") },
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    androidx.compose.material3.Icon(
                                        imageVector = image,
                                        contentDescription = null
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = age,
                            onValueChange = { age = it },
                            label = { Text("Age") },
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = mobileNumber,
                            onValueChange = { mobileNumber = it },
                            label = { Text("Mobile Number") },
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = gender,
                                onValueChange = { gender = it },
                                label = { Text("Gender") },
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expanded = !expanded },
                                readOnly = true,
                                trailingIcon = {
                                    Icon(
                                        imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                                        contentDescription = null
                                    )
                                }
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                genders.forEach { label ->
                                    DropdownMenuItem(onClick = {
                                        gender = label
                                        expanded = false
                                    }) {
                                        Text(text = label)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = {
                                if (
                                    name.isNotEmpty() && mobileNumber.isNotEmpty() &&
                                    age.isNotEmpty() && gender.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
                                ) {
                                    viewModel.signUp(
                                        user = User(
                                            _id = "",
                                            name = name,
                                            email = email,
                                            age = age.toInt(),
                                            phone = mobileNumber.toLong(),
                                            gender = gender,
                                            lookingForRoommate = false
                                        ),
                                        password = password
                                    )
                                }
                            },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Sign Up", fontSize = 18.sp)
                        }
                    }
                    // Add the image at the bottom
                    Image(
                        painter = painterResource(id = R.drawable.curve),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                    )
                }
            }
            is State.Success -> {
                ctx.startActivity(Intent(ctx, HomeActivity::class.java))
                (ctx as Activity).finish()
            }
        }
    }
}
