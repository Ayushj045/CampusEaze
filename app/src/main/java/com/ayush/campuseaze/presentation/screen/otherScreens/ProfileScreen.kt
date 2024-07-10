package com.ayush.campuseaze.presentation.screen.otherScreens

import android.app.Activity
import android.provider.ContactsContract.Profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ayush.campuseaze.R
import com.ayush.campuseaze.presentation.components.Loading
import com.ayush.campuseaze.presentation.components.Toast
import com.ayush.campuseaze.presentation.viewmodel.ProfileViewModel
import com.ayush.campuseaze.utils.State

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    userId: String
) {

    val ctx = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getUser(userId)
    }
    var isDarkMode by remember { mutableStateOf(false) }

    viewModel.user.collectAsState().value.let {
        when(it) {
            is State.Error -> Toast(ctx, it.message)
            State.Loading -> Loading()
            State.None -> {}
            is State.Success -> {
                val user = it.data

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (isDarkMode) Color.DarkGray else Color.White)
                        .padding(16.dp)
                ) {

                    CustomTitleBar("Profile")

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pfp),
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = user.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "+91 ${user.phone}",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Complete Your Profile >",
                                    fontSize = 14.sp,
                                    color = Color(0xFF4CAF50)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingItem(
                        text = "Dark Mode",
                        trailingContent = {
                            Switch(
                                checked = isDarkMode,
                                onCheckedChange = { isDarkMode = it }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingItem(
                        text = "Settings",
                        trailingContent = {}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingItem(
                        text = "About Us",
                        trailingContent = {}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingItem(
                        text = "Customer Support",
                        trailingContent = {}
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTitleBar(title:String) {

    val activity = LocalContext.current as Activity

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, start = 5.dp, bottom = 7.dp)) {

        Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null ,
            modifier = Modifier
                .padding(top = 10.dp, end = 5.dp)
                .clickable {
                    activity.finish()
                })
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, modifier = Modifier.padding(start = 5.dp,top=13.dp))


    }
}

@Composable
fun SettingItem(
    text: String,
    trailingContent: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.LightGray, RoundedCornerShape(19.dp))
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            trailingContent()
        }
    }
}