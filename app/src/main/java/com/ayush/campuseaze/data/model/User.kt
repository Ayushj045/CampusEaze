package com.ayush.campuseaze.data.model

data class User(
    val _id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: Long = 0L,
    val age: Int = 0,
    val lookingForRoommate: Boolean = false,
    val gender: String = "",
)
